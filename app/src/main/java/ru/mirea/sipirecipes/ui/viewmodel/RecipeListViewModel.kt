package ru.mirea.sipirecipes.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.mirea.sipirecipes.data.repository.RecipeRepository
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(private val recipeRepository: RecipeRepository) :
    ViewModel() {

    val recipesList = recipeRepository.recipes

    init {
        viewModelScope.launch(Dispatchers.IO) {
            recipeRepository.getAllRecipes()
        }
    }
}