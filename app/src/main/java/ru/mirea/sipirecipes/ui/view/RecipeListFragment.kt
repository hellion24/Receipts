package ru.mirea.sipirecipes.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import ru.mirea.sipirecipes.R
import ru.mirea.sipirecipes.data.network.ResultWrapper
import ru.mirea.sipirecipes.databinding.RecipeListFragmentBinding
import ru.mirea.sipirecipes.ui.adapter.RecipeListAdapter
import ru.mirea.sipirecipes.ui.viewmodel.RecipeListViewModel
import ru.mirea.sipirecipes.utility.Constants
import javax.inject.Inject

@AndroidEntryPoint
class RecipeListFragment : Fragment() {
    private val viewModel: RecipeListViewModel by viewModels()

    @Inject
    lateinit var adapter: RecipeListAdapter

    private var _binding: RecipeListFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.recipe_list_fragment, container, false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.list.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.clickListener.onItemClick = {
            val bundle = bundleOf(Constants.RECIPE_UUID_DETAILS_TAG to it.uuid.toString())
            Navigation.findNavController(view)
                .navigate(R.id.action_recipeShortFragment_to_recipeDetailsFragment, bundle)
        }

        viewModel.getRecipes()
        viewModel.recipes.observe(viewLifecycleOwner) {
            when (it) {
                is ResultWrapper.Success -> {
                    adapter.submitList(it.data)
                    Log.d("RecipeListFragment", "Success!")
                }
                is ResultWrapper.Error -> {
                    Toast.makeText(context, it.errorMessage, Toast.LENGTH_SHORT).show()
                    Log.d("RecipeListFragment", "Error")
                }
                is ResultWrapper.Loading -> {
                    // show progress
                    Log.d("RecipeListFragment", "Loading...")
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.list.adapter = null
        _binding = null
    }
}