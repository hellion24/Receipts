package ru.mirea.sipirecipes.data.model

data class UserEntity(
    val uuid: String,
    val login: String,
    var password: String,
    val nickname: String?,
    val userRole: UserRole,
    val status: UserStatus,
    val firstName: String,
    val lastName: String?
)
