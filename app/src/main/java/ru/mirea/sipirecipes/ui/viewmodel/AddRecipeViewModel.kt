package ru.mirea.sipirecipes.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.mirea.sipirecipes.data.model.NewRecipe
import ru.mirea.sipirecipes.data.model.RecipeStatus
import ru.mirea.sipirecipes.data.model.RecipeSummary
import ru.mirea.sipirecipes.data.network.ResultWrapper
import ru.mirea.sipirecipes.data.repository.RecipeRepository
import java.math.BigDecimal
import javax.inject.Inject

@HiltViewModel
class AddRecipeViewModel @Inject constructor(private val recipeRepository: RecipeRepository) :
    ViewModel() {

    private val _addRecipeResult: MutableLiveData<ResultWrapper<RecipeSummary>> = MutableLiveData()
    val addRecipeResult: LiveData<ResultWrapper<RecipeSummary>> = _addRecipeResult

    fun addRecipe(
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
        val newRecipe = NewRecipe(
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
        viewModelScope.launch {
            recipeRepository.addRecipe(newRecipe).collect { value ->
                _addRecipeResult.value = value
            }
        }
    }
}