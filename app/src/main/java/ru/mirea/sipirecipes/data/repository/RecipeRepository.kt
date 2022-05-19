package ru.mirea.sipirecipes.data.repository

import androidx.lifecycle.MutableLiveData
import ru.mirea.sipirecipes.data.model.RecipeSummary
import ru.mirea.sipirecipes.data.network.RecipeService
import javax.inject.Inject

class RecipeRepository @Inject constructor(private val recipeService: RecipeService) {
    var recipes: MutableLiveData<List<RecipeSummary>> = MutableLiveData()

    suspend fun getAllRecipes() = recipeService.getRecipesSummary()
}