package ru.mirea.sipirecipes.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import ru.mirea.sipirecipes.R
import ru.mirea.sipirecipes.data.network.ResultWrapper
import ru.mirea.sipirecipes.databinding.RegisterFragmentBinding
import ru.mirea.sipirecipes.ui.viewmodel.RegisterViewModel

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private var _binding: RegisterFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = RegisterFragmentBinding.inflate(layoutInflater, container, false)
        binding.registerProgressBar.visibility = View.GONE

        binding.signUpBtn.setOnClickListener {
            if (
                binding.editTextLogin.text.toString().isNotEmpty() &&
                binding.editTextPassword.text.toString().isNotEmpty() &&
                binding.editTextRepeatPassword.text.toString().isNotEmpty() &&
                binding.editTextFirstName.text.toString().isNotEmpty() &&
                binding.editTextLastName.text.toString().isNotEmpty()
            ) {
                viewModel.performRegistration(
                    binding.editTextLogin.text.toString(),
                    binding.editTextPassword.text.toString(),
                    binding.editTextFirstName.text.toString(),
                    binding.editTextLastName.text.toString()
                )
            } else {
                Toast.makeText(
                    context,
                    "Ошибка ввода данных",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.registeredUserResult.observe(viewLifecycleOwner) {
            when (it) {
                is ResultWrapper.Success -> {
                    // redirect to home here
                    binding.registerProgressBar.visibility = View.GONE
                    getView()?.let { itView ->
                        Navigation.findNavController(itView)
                            .navigate(R.id.action_nav_register_to_recipeShortFragment)
                    }
                }
                is ResultWrapper.Error -> {
                    binding.registerProgressBar.visibility = View.GONE
                    Toast.makeText(context, it.errorMessage, Toast.LENGTH_SHORT).show()
                }
                is ResultWrapper.Loading -> {
                    // show progress
                    binding.registerProgressBar.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}