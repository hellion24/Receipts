package ru.mirea.sipirecipes.ui.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.mirea.sipirecipes.ui.viewmodel.RecipeDetailsViewModel

@AndroidEntryPoint
class RecipeDetailsFragment : Fragment() {

    private var _binding: RecipeDetailsFragment? = null
    private val binding get() = _binding!!
    private val viewModel: RecipeDetailsViewModel by viewModels()

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}