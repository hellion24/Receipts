package ru.mirea.sipirecipes.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.mirea.sipirecipes.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null

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

        // return inflated binding
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}