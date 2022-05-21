package ru.mirea.sipirecipes.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.mirea.sipirecipes.data.model.AuthenticationDto
import ru.mirea.sipirecipes.data.model.UserEntity
import ru.mirea.sipirecipes.data.network.ResultWrapper
import ru.mirea.sipirecipes.data.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private val _loggedInUser: MutableLiveData<ResultWrapper<UserEntity>> = MutableLiveData()
    val loggedInUser: LiveData<ResultWrapper<UserEntity>> = _loggedInUser

    fun performLogin(dto: AuthenticationDto) = viewModelScope.launch {
        userRepository.login(dto).collect { value ->
            _loggedInUser.value = value
        }
    }
}