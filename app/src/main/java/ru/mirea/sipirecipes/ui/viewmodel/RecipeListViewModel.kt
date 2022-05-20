package ru.mirea.sipirecipes.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.mirea.sipirecipes.data.model.RecipeSummary
import ru.mirea.sipirecipes.data.network.ResultWrapper
import ru.mirea.sipirecipes.data.repository.RecipeRepository
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(private val recipeRepository: RecipeRepository) :
    ViewModel() {

    private val _recipes: MutableLiveData<ResultWrapper<List<RecipeSummary>>> = MutableLiveData()
    val recipes: LiveData<ResultWrapper<List<RecipeSummary>>> = _recipes

    fun getRecipes() = viewModelScope.launch {
        recipeRepository.getRecipesSummary().collect { 
            values ->  _recipes.value = values
        }
    }
}