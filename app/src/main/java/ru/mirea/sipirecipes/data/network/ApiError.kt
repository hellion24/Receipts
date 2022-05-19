package ru.mirea.sipirecipes.data.network

import com.google.gson.annotations.SerializedName

data class ApiError(

    @SerializedName("responseCode")
    val responseCode: Int,

    @SerializedName("responseMessage")
    val responseMessage: String
)
