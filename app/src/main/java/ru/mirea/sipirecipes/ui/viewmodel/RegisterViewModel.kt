package ru.mirea.sipirecipes.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.mirea.sipirecipes.data.model.UserEntity
import ru.mirea.sipirecipes.data.model.UserRole
import ru.mirea.sipirecipes.data.model.UserStatus
import ru.mirea.sipirecipes.data.network.ResultWrapper
import ru.mirea.sipirecipes.data.repository.UserRepository
import java.util.*
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {

    private val _registeredUserResult: MutableLiveData<ResultWrapper<UserEntity>> =
        MutableLiveData()
    val registeredUserResult: LiveData<ResultWrapper<UserEntity>> = _registeredUserResult

    fun performRegistration(login: String, password: String, firstName: String, lastName: String) {
        val userToRegister = UserEntity(
            UUID.randomUUID().toString(),// ignored on server for register call
            login,
            password,
            null,
            UserRole.USER, // ignored on server
            UserStatus.ACTIVE, // ignored on server
            firstName,
            lastName
        )
        viewModelScope.launch {
            userRepository.register(userToRegister).collect { value ->
                _registeredUserResult.value = value
            }
        }
    }
}