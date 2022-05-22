package ru.mirea.sipirecipes.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.mirea.sipirecipes.data.model.NewRecipe
import ru.mirea.sipirecipes.data.model.RecipeInfo
import ru.mirea.sipirecipes.data.model.RecipeSummary
import ru.mirea.sipirecipes.data.network.RecipeService
import ru.mirea.sipirecipes.data.network.ResultWrapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeRepository @Inject constructor(
    private val recipeService: RecipeService,
    private val userRepository: UserRepository
) : BaseRepository("RecipeRepository") {
    // maybe save livedata here idk ?

    suspend fun getRecipesSummary(): Flow<ResultWrapper<List<RecipeSummary>>> {
        return flow<ResultWrapper<List<RecipeSummary>>> {
            emit(ResultWrapper.Loading())
            emit(safeApiCall { recipeService.getRecipesSummary() })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun addRecipe(newRecipeDto: NewRecipe): Flow<ResultWrapper<RecipeSummary>> {
        return flow {
            emit(ResultWrapper.Loading())
            emit(
                safeApiCall {
                    recipeService.addRecipe(
                        userRepository.getAuthHeader(),
                        newRecipeDto
                    )
                }
            )
        }
    }

    suspend fun getRecipeDetails(uuid: String): Flow<ResultWrapper<RecipeInfo>> {
        return flow {
            emit(ResultWrapper.Loading())
            emit(safeApiCall { recipeService.getRecipeInfo(uuid) })
        }
    }
}