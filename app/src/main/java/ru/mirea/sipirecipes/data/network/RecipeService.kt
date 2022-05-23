package ru.mirea.sipirecipes.data.network

import retrofit2.Response
import retrofit2.http.*
import ru.mirea.sipirecipes.data.model.NewRecipe
import ru.mirea.sipirecipes.data.model.RecipeInfo
import ru.mirea.sipirecipes.data.model.RecipeSummary

interface RecipeService {

    @GET("/api/recipe/recipes")
    suspend fun getRecipesSummary(): Response<List<RecipeSummary>>

    @GET("/api/recipe/info/{uuid}")
    suspend fun getRecipeInfo(@Path("uuid") uuid: String): Response<RecipeInfo>

    @POST("/api/recipe/add")
    suspend fun addRecipe(
        @Header("Authorization") authHeader: String,
        @Body recipeDto: NewRecipe
    ): Response<RecipeSummary>

    @PUT("/api/recipe/update/{uuid}")
    suspend fun updateRecipe(
        @Header("Authorization") authHeader: String,
        @Path("uuid") uuid: String,
        @Body recipeDto: NewRecipe
    ): Response<RecipeSummary>

    @POST("/api/recipe/addFavorite/{uuid")
    suspend fun addToFavorite(
        @Header("Authorization") authHeader: String,
        @Path("uuid") uuid: String
    )

    @POST("/api/recipe/removeFavorite/{uuid")
    suspend fun removeFromFavorite(
        @Header("Authorization") authHeader: String,
        @Path("uuid") uuid: String
    )

    @DELETE("/api/recipe/delete/{uuid}")
    suspend fun deleteRecipe(
        @Header("Authorization") authHeader: String,
        @Path("uuid") uuid: String
    ): Response<Unit>
}