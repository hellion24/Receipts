package ru.mirea.sipirecipes.data.model

data class UserEntity(
    val uuid: String,
    val login: String,
    val password: String,
    val nickname: String?,
    val userRole: String,
    val status: String,
    val firstName: String?,
    val lastName: String?
)
