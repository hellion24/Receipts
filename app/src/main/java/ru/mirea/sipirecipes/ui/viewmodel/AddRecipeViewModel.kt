package ru.mirea.sipirecipes.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.mirea.sipirecipes.data.model.NewRecipe
import ru.mirea.sipirecipes.data.model.RecipeInfo
import ru.mirea.sipirecipes.data.model.RecipeStatus
import ru.mirea.sipirecipes.data.model.RecipeSummary
import ru.mirea.sipirecipes.data.network.ResultWrapper
import ru.mirea.sipirecipes.data.repository.RecipeRepository
import java.math.BigDecimal
import javax.inject.Inject

@HiltViewModel
class AddRecipeViewModel @Inject constructor(private val recipeRepository: RecipeRepository) :
    ViewModel() {

    private val _uploadRecipeResult: MutableLiveData<ResultWrapper<RecipeSummary>> =
        MutableLiveData()
    val uploadRecipeResult: LiveData<ResultWrapper<RecipeSummary>> = _uploadRecipeResult

    private val _loadRecipeResult: MutableLiveData<ResultWrapper<RecipeInfo>> = MutableLiveData()
    val loadRecipeResult: LiveData<ResultWrapper<RecipeInfo>> = _loadRecipeResult

    fun addOrUpdateRecipe(
        updateRecipe: Boolean,
        uuidToUpdate: String?,
        category: String,
        name: String,
        complexity: Int,
        description: String,
        instructions: String,
        price: BigDecimal,
        durationHours: Int,
        durationMinutes: Int,
        portion: String,
        calories: BigDecimal,
        fats: BigDecimal,
        proteins: BigDecimal,
        carbohydrates: BigDecimal,
    ) {
        val recipeDto = NewRecipe(
            category,
            null,
            name,
            complexity,
            description,
            instructions,
            price,
            durationHours,
            durationMinutes,
            portion,
            calories,
            fats,
            proteins,
            carbohydrates,
            RecipeStatus.APPROVED // ignored on server
        )
        if (updateRecipe) {
            viewModelScope.launch {
                recipeRepository.updateRecipe(uuidToUpdate!!, recipeDto).collect { updatedRecipe ->
                    _uploadRecipeResult.value = updatedRecipe
                }
            }
        } else {
            viewModelScope.launch {
                recipeRepository.addRecipe(recipeDto).collect { addedRecipe ->
                    _uploadRecipeResult.value = addedRecipe
                }
            }
        }
    }

    fun loadRecipeInfo(uuid: String) {
        viewModelScope.launch {
            recipeRepository.getRecipeDetails(uuid).collect { loadedRecipe ->
                _loadRecipeResult.value = loadedRecipe
            }
        }
    }
}