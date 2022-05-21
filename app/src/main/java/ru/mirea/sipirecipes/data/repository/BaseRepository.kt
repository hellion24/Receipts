package ru.mirea.sipirecipes.data.repository

import android.util.Log
import retrofit2.Response
import ru.mirea.sipirecipes.data.network.ResultWrapper

abstract class BaseRepository(protected val TAG: String) {

    protected suspend fun <T> safeApiCall(call: suspend () -> Response<T>): ResultWrapper<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return ResultWrapper.Success(body)
                }
            }
            Log.d(TAG, "Call failed with ${response.message()}")
            return formatError(" ${response.code()} ${response.message()}")
        } catch (exception: Exception) {
            exception.message?.let {
                Log.d(TAG, "Call failed with exception: ${exception.message}")
                return ResultWrapper.Error("Call failed with exception: ${exception.message}")
            }
            Log.d(TAG, "Call exception NO message.")
            return ResultWrapper.Error("Exception with null message.")
        }
    }

    private fun <T> formatError(errorMessage: String): ResultWrapper<T> {
        return ResultWrapper.Error("Call failed with: $errorMessage")
    }
}