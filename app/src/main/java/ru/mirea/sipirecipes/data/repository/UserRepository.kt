package ru.mirea.sipirecipes.data.repository

import android.util.Base64
import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.mirea.sipirecipes.data.model.AuthenticationDto
import ru.mirea.sipirecipes.data.model.UserEntity
import ru.mirea.sipirecipes.data.model.UserRole
import ru.mirea.sipirecipes.data.network.ResultWrapper
import ru.mirea.sipirecipes.data.network.UserService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val userService: UserService) :
    BaseRepository("UserRepository") {
    var user: MutableLiveData<UserEntity> = MutableLiveData()
    var isPrivileged: MutableLiveData<Boolean> = MutableLiveData()

    suspend fun login(dto: AuthenticationDto): Flow<ResultWrapper<UserEntity>> {
        return flow {
            emit(ResultWrapper.Loading())
            val result = safeApiCall { userService.login(dto) }
            if (result.isSuccessful()) {
                user.postValue(result.data)
                isPrivileged.postValue(result.data!!.status == UserRole.ADMIN.toString())
                Log.d(TAG, "Logged in as: ${result.data.login}")
            }
            emit(result)
        }
    }

    fun logout() {
        isPrivileged.postValue(null)
        user.postValue(null)
    }

    fun isUserPresent(): Boolean {
        return user.value != null
    }

    fun getAuthHeader(): String {
        val credentials = user.value!!.login + ":" + user.value!!.password
        return "Basic " + Base64.encodeToString(
            credentials.toByteArray(Charsets.UTF_8),
            Base64.DEFAULT
        ).replace("\n", "")
    }
}