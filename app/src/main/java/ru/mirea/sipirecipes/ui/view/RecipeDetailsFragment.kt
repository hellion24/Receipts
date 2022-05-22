package ru.mirea.sipirecipes.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.mirea.sipirecipes.R
import ru.mirea.sipirecipes.data.network.ResultWrapper
import ru.mirea.sipirecipes.databinding.RecipeDetailsFragmentBinding
import ru.mirea.sipirecipes.ui.viewmodel.RecipeDetailsViewModel

@AndroidEntryPoint
class RecipeDetailsFragment : Fragment() {

    private var _binding: RecipeDetailsFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RecipeDetailsViewModel by viewModels()
    private var recipeUuid: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.recipe_details_fragment, container, false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        recipeUuid = arguments?.getString("recipeUuid")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.recipeDetailsResult.observe(viewLifecycleOwner) {
            when (it) {
                is ResultWrapper.Loading -> {
                    // no progress bar :/
                }
                is ResultWrapper.Error -> {
                    // add back navigation? or reload button?
                    Toast.makeText(context, it.errorMessage, Toast.LENGTH_SHORT).show()
                }
                is ResultWrapper.Success -> {
                    binding.data = it.data
                }
            }
        }
        viewModel.getRecipeDetails(recipeUuid!!)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}