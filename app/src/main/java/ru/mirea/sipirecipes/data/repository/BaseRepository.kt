package ru.mirea.sipirecipes.data.repository

import android.util.Log
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import ru.mirea.sipirecipes.data.network.ApiError
import ru.mirea.sipirecipes.data.network.ResultWrapper
import java.io.IOException

abstract class BaseRepository(protected val TAG: String) {

    companion object {
        const val RESPONSE_CODE_TAG = "responseCode"
        const val RESPONSE_MESSAGE_TAG = "responseMessage"
    }

    protected suspend fun <T> safeApiCall(call: suspend () -> Response<T>): ResultWrapper<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return ResultWrapper.Success(body)
                }
            }
            Log.d(TAG, "Call failed: $response")

            response.errorBody()?.let {
                try {
                    // false positive warning ??
                    val apiError = parseApiError(response.errorBody()!!.string())
                    Log.d(TAG, "Parsed error body: $apiError")
                    return formatError("${apiError.responseCode}; ${apiError.responseMessage}")
                } catch (ex: JSONException) {
                    Log.d(TAG, "Could not parse custom error response.")
                    ex.message?.let { it1 -> Log.d(TAG, it1) }
                } catch (ex: IOException) {
                    Log.d(TAG, "Could not parse custom error response.")
                    ex.message?.let { it1 -> Log.d(TAG, it1) }
                }
            }

            return formatError("${response.code()}; ${response.message()}")
        } catch (exception: Exception) {
            exception.message?.let {
                Log.d(TAG, "Call failed with exception: ${exception.message}")
                return ResultWrapper.Error("${exception.message}")
            }
            Log.d(TAG, "Call exception NO message.")
            return ResultWrapper.Error("Exception with null message.")
        }
    }

    private fun parseApiError(responseBodyString: String): ApiError {
        val errorBodyJson = JSONObject(responseBodyString)
        return ApiError(
            errorBodyJson.getInt(RESPONSE_CODE_TAG),
            errorBodyJson.getString(RESPONSE_MESSAGE_TAG)
        )
    }

    private fun <T> formatError(errorMessage: String): ResultWrapper<T> {
        return ResultWrapper.Error("Call failed: $errorMessage")
    }
}