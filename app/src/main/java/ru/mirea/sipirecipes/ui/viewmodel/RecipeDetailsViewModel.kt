package ru.mirea.sipirecipes.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.mirea.sipirecipes.data.model.RecipeInfo
import ru.mirea.sipirecipes.data.network.ResultWrapper
import ru.mirea.sipirecipes.data.repository.RecipeRepository
import ru.mirea.sipirecipes.data.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    private val userRepository: UserRepository
) :
    ViewModel() {

    private val _recipeDetailsResult: MutableLiveData<ResultWrapper<RecipeInfo>> = MutableLiveData()
    val recipeDetailsResult: LiveData<ResultWrapper<RecipeInfo>> = _recipeDetailsResult

    val isUserPrivileged: LiveData<Boolean> = userRepository.isPrivileged

    fun getRecipeDetails(uuid: String) {
        viewModelScope.launch {
            recipeRepository.getRecipeDetails(uuid).collect { callResult ->
                _recipeDetailsResult.value = callResult
            }
        }
    }
}