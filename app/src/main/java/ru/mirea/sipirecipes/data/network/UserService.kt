package ru.mirea.sipirecipes.data.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import ru.mirea.sipirecipes.data.model.AuthenticationDto
import ru.mirea.sipirecipes.data.model.UserEntity

interface UserService {

    @GET("/api/user/login")
    suspend fun login(@Body dto: AuthenticationDto): Response<UserEntity>

    @POST("/api/user/register")
    suspend fun register(@Body dto: UserEntity): Response<UserEntity>
}