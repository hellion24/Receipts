package ru.mirea.sipirecipes.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.mirea.sipirecipes.data.model.RecipeInfo
import ru.mirea.sipirecipes.data.network.ResultWrapper
import ru.mirea.sipirecipes.databinding.UploadRecipeFragmentBinding
import ru.mirea.sipirecipes.ui.viewmodel.UploadRecipeViewModel
import ru.mirea.sipirecipes.utility.Constants
import java.math.BigDecimal

@AndroidEntryPoint
class UploadRecipeFragment : Fragment() {

    private var _binding: UploadRecipeFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UploadRecipeViewModel by viewModels()

    private var recievedUuid: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = UploadRecipeFragmentBinding.inflate(layoutInflater, container, false)
        binding.progressBar2.visibility = View.GONE

        recievedUuid = arguments?.getString(Constants.RECIPE_UUID_UPDATE_TAG, null)

        binding.addRecipeBtn.setOnClickListener {
            if (checkFields()) {
                if (recievedUuid != null) {
                    viewModel.addOrUpdateRecipe(
                        true,
                        recievedUuid,
                        binding.etCategory.text.toString(),
                        binding.etRecipeName.text.toString(),
                        binding.etComplexity.text.toString().toInt(),
                        binding.etDescription.text.toString(),
                        binding.etInstructions.text.toString(),
                        binding.etPrice.text.toString().toBigDecimal(),
                        binding.etHours.text.toString().toInt(),
                        binding.etMinutes.text.toString().toInt(),
                        binding.etPortion.text.toString(),
                        binding.etCalories.text.toString().toBigDecimal(),
                        binding.etFats.text.toString().toBigDecimal(),
                        binding.etProteins.text.toString().toBigDecimal(),
                        binding.etCarbohydrates.text.toString().toBigDecimal()
                    )
                } else {
                    // add recipe
                    viewModel.addOrUpdateRecipe(
                        false,
                        null,
                        binding.etCategory.text.toString(),
                        binding.etRecipeName.text.toString(),
                        binding.etComplexity.text.toString().toInt(),
                        binding.etDescription.text.toString(),
                        binding.etInstructions.text.toString(),
                        binding.etPrice.text.toString().toBigDecimal(),
                        binding.etHours.text.toString().toInt(),
                        binding.etMinutes.text.toString().toInt(),
                        binding.etPortion.text.toString(),
                        binding.etCalories.text.toString().toBigDecimal(),
                        binding.etFats.text.toString().toBigDecimal(),
                        binding.etProteins.text.toString().toBigDecimal(),
                        binding.etCarbohydrates.text.toString().toBigDecimal()
                    )
                }
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObservers()
        recievedUuid?.let {
            viewModel.loadRecipeInfo(recievedUuid!!)
        }
    }

    private fun setObservers() {
        viewModel.uploadRecipeResult.observe(viewLifecycleOwner) {
            when (it) {
                is ResultWrapper.Success -> {
                    binding.progressBar2.visibility = View.GONE
                    // clear fields
                    if (recievedUuid == null) {
                        clearFields()
                    }
                    Toast.makeText(context, "Рецепт загружен!", Toast.LENGTH_SHORT).show()
                }
                is ResultWrapper.Error -> {
                    binding.progressBar2.visibility = View.GONE
                    Toast.makeText(context, it.errorMessage, Toast.LENGTH_SHORT).show()
                }
                is ResultWrapper.Loading -> {
                    // show progress
                    binding.progressBar2.visibility = View.VISIBLE
                }
            }
        }
        viewModel.loadRecipeResult.observe(viewLifecycleOwner) {
            when (it) {
                is ResultWrapper.Success -> {
                    binding.progressBar2.visibility = View.GONE
                    // set fields
                    setFields(it.data!!)
                    Toast.makeText(context, "Информация о рецепте загружена.", Toast.LENGTH_SHORT)
                        .show()
                }
                is ResultWrapper.Error -> {
                    binding.progressBar2.visibility = View.GONE
                    Toast.makeText(context, it.errorMessage, Toast.LENGTH_SHORT).show()
                }
                is ResultWrapper.Loading -> {
                    // show progress
                    binding.progressBar2.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun checkFields(): Boolean {
        val fieldsNotEmpty = binding.etRecipeName.text.toString().isNotEmpty() &&
                binding.etCategory.text.toString().isNotEmpty() &&
                binding.etDescription.text.toString().isNotEmpty() &&
                binding.etComplexity.text.toString().isNotEmpty() &&
                binding.etInstructions.text.toString().isNotEmpty() &&
                binding.etHours.text.toString().isNotEmpty() &&
                binding.etMinutes.text.toString().isNotEmpty() &&
                binding.etPortion.text.toString().isNotEmpty() &&
                binding.etCalories.text.toString().isNotEmpty() &&
                binding.etFats.text.toString().isNotEmpty() &&
                binding.etCarbohydrates.text.toString().isNotEmpty() &&
                binding.etProteins.text.toString().isNotEmpty() &&
                binding.etPrice.text.toString().isNotEmpty()

        if (!fieldsNotEmpty) {
            Toast.makeText(context, "Недостаточно данных.", Toast.LENGTH_SHORT).show()
        }

        return fieldsNotEmpty &&
                checkBigDecimalConversion(binding.etPrice.text.toString()) &&
                checkBigDecimalConversion(binding.etCalories.text.toString()) &&
                checkBigDecimalConversion(binding.etFats.text.toString()) &&
                checkBigDecimalConversion(binding.etCarbohydrates.text.toString()) &&
                checkBigDecimalConversion(binding.etProteins.text.toString()) &&
                checkIntConversion(binding.etComplexity.text.toString()) &&
                checkIntConversion(binding.etHours.text.toString(), false) &&
                checkIntConversion(binding.etMinutes.text.toString())
    }

    private fun checkBigDecimalConversion(value: String, checkPositive: Boolean = true): Boolean {
        return try {
            val convertedValue = value.toBigDecimal()
            if (checkPositive) {
                convertedValue > BigDecimal(0)
            } else {
                true
            }
        } catch (ex: NumberFormatException) {
            Toast.makeText(context, "Некорректное числовое значение!", Toast.LENGTH_SHORT)
                .show()
            false
        }
    }

    private fun checkIntConversion(value: String, checkPositive: Boolean = true): Boolean {
        return try {
            val convertedValue = value.toInt()
            if (checkPositive) {
                convertedValue > 0
            } else {
                true
            }
        } catch (ex: NumberFormatException) {
            Toast.makeText(context, "Некорректное числовое значение!", Toast.LENGTH_SHORT)
                .show()
            false
        }
    }

    private fun clearFields() {
        binding.etRecipeName.text.clear()
        binding.etCategory.text.clear()
        binding.etDescription.text.clear()
        binding.etComplexity.text.clear()
        binding.etInstructions.text.clear()
        binding.etHours.text.clear()
        binding.etMinutes.text.clear()
        binding.etPortion.text.clear()
        binding.etCalories.text.clear()
        binding.etFats.text.clear()
        binding.etCarbohydrates.text.clear()
        binding.etProteins.text.clear()
        binding.etPrice.text.clear()
    }

    private fun setFields(loadedRecipe: RecipeInfo) {
        binding.etRecipeName.setText(loadedRecipe.name)
        binding.etCategory.setText(loadedRecipe.category)
        binding.etDescription.setText(loadedRecipe.description)
        binding.etComplexity.setText(loadedRecipe.complexity.toString())
        binding.etInstructions.setText(loadedRecipe.instructions)
        binding.etHours.setText(loadedRecipe.durationHours.toString())
        binding.etMinutes.setText(loadedRecipe.durationMinutes.toString())
        binding.etPortion.setText(loadedRecipe.portion)
        binding.etCalories.setText(loadedRecipe.calories.toString())
        binding.etFats.setText(loadedRecipe.fats.toString())
        binding.etCarbohydrates.setText(loadedRecipe.carbohydrates.toString())
        binding.etProteins.setText(loadedRecipe.proteins.toString())
        binding.etPrice.setText(loadedRecipe.price.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}