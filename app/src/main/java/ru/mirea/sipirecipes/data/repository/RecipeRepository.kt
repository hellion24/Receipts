package ru.mirea.sipirecipes.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.mirea.sipirecipes.data.model.RecipeSummary
import ru.mirea.sipirecipes.data.network.RecipeService
import ru.mirea.sipirecipes.data.network.ResultWrapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeRepository @Inject constructor(private val recipeService: RecipeService) : BaseRepository("RecipeRepository") {
    // maybe save livedata here idk ?

    suspend fun getRecipesSummary(): Flow<ResultWrapper<List<RecipeSummary>>> {
        return flow<ResultWrapper<List<RecipeSummary>>> {
            emit(safeApiCall { recipeService.getRecipesSummary() })
        }.flowOn(Dispatchers.IO)
    }

}