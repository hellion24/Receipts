package ru.mirea.sipirecipes.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.mirea.sipirecipes.data.model.AuthenticationDto
import ru.mirea.sipirecipes.data.network.ResultWrapper
import ru.mirea.sipirecipes.databinding.FragmentLoginBinding
import ru.mirea.sipirecipes.ui.viewmodel.LoginViewModel

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val viewModel: LoginViewModel by viewModels()

    // This property is only valid between onCreateView and onDestroyView
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // set binding and inflate it here
        // also set callback here
        _binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        binding.loginProgress.visibility = View.GONE

        binding.loginBtn.setOnClickListener {
            if (binding.login.text.toString().isNotEmpty() && binding.password.text.isNotEmpty()) {
                viewModel.performLogin(
                    AuthenticationDto(
                        binding.login.text.toString(),
                        binding.password.text.toString()
                    )
                )
            } else {
                Toast.makeText(
                    context,
                    "Введите логин и пароль!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // return inflated binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loggedInUser.observe(viewLifecycleOwner) {
            when (it) {
                is ResultWrapper.Success -> {
                    // redirect to home here
                    binding.loginProgress.visibility = View.GONE
                }
                is ResultWrapper.Error -> {
                    binding.loginProgress.visibility = View.GONE
                    Toast.makeText(context, it.errorMessage, Toast.LENGTH_SHORT).show()
                }
                is ResultWrapper.Loading -> {
                    // show progress
                    binding.loginProgress.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}