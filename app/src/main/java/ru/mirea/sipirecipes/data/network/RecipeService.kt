package ru.mirea.sipirecipes.data.network

import retrofit2.Response
import retrofit2.http.GET
import ru.mirea.sipirecipes.data.model.RecipeSummary

interface RecipeService {

    @GET("/api/recipes")
    suspend fun getRecipesSummary(): Response<List<RecipeSummary>>
}