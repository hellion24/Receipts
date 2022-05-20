package ru.mirea.sipirecipes.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import ru.mirea.sipirecipes.data.model.RecipeSummary
import ru.mirea.sipirecipes.data.network.RecipeService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeRepository @Inject constructor(private val recipeService: RecipeService) {
    var recipes: MutableLiveData<List<RecipeSummary>> = MutableLiveData()

    suspend fun getAllRecipes() {
        val recipeResponse = recipeService.getRecipesSummary()

        if (recipeResponse.isSuccessful) {
            recipes.postValue(recipeResponse.body())
        } else {
            Log.d("Recipe repository", "Request failed.")
            Log.d("Recipe repository", recipeResponse.toString())
        }
    }
}