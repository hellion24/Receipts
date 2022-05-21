package ru.mirea.sipirecipes.data.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import ru.mirea.sipirecipes.data.model.NewRecipe
import ru.mirea.sipirecipes.data.model.RecipeSummary

interface RecipeService {

    @GET("/api/recipe/recipes")
    suspend fun getRecipesSummary(): Response<List<RecipeSummary>>

    @POST("/api/recipe/add")
    suspend fun addRecipe(
        @Header("Authorization") authHeader: String,
        @Body recipeDto: NewRecipe
    ): Response<RecipeSummary>
}