package ru.mirea.sipirecipes.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import ru.mirea.sipirecipes.data.model.AuthenticationDto
import ru.mirea.sipirecipes.data.model.UserEntity
import ru.mirea.sipirecipes.data.network.UserService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val userService: UserService) {
    var user: MutableLiveData<UserEntity> = MutableLiveData()

    suspend fun login(dto: AuthenticationDto) {
        val userResponse = userService.login(dto)

        if (userResponse.isSuccessful) {
            user.postValue(userResponse.body())
        } else {
            Log.d("User repository", "Request failed.")
            Log.d("User repository", userResponse.toString())
        }
    }

    fun isUserPresent(): Boolean {
        return user.value != null
    }
}