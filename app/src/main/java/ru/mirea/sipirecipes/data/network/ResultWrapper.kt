package ru.mirea.sipirecipes.data.network

sealed class ResultWrapper<out T>(
    val data: T? = null,
    val errorMessage: String? = null,
    val exception: Exception? = null
) {
    class Success<T>(data: T) : ResultWrapper<T>(data)

    class Error<T>(errorMessage: String, data: T? = null) : ResultWrapper<T>(data, errorMessage)

    class Loading<T> : ResultWrapper<T>()
}