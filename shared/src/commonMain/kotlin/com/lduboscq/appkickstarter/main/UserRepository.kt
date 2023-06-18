package com.lduboscq.appkickstarter.main

interface UserRepository {
    suspend fun getUser(
        userName: String,
        email: String,
        password: String,
        confirmPassword: String
    ): UserData?
    suspend fun getUser(
        email: String,
        password: String,
    ): LoginUserData?

    suspend fun addUser(userData: UserData): UserData?

    suspend fun deleteOneUser(userName: String):UserData?

    suspend fun updateUser(userName: String,password: String,confirmPassword: String):UserData?

    suspend fun getAllUsers(userName: String):UserData?

    suspend fun getUserName(email: String)
}