package ru.mirea.sipirecipes.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.mirea.sipirecipes.data.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {
    val isPrivileged: LiveData<Boolean> = userRepository.isPrivileged
    val isUserPresent: LiveData<Boolean> = userRepository.isLoggedIn
}