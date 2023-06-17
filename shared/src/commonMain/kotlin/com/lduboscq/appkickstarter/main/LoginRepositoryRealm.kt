package com.lduboscq.appkickstarter.main

import io.realm.kotlin.Realm

abstract class LoginRepositoryRealm : UserRepository {

    lateinit var realm: Realm

    abstract suspend fun setupRealmSync()

    suspend fun convertToUserData(user: User?): UserData? {
        if (!this::realm.isInitialized) {
            setupRealmSync()
        }

        var userData: UserData? = null
        realm.write {
            if (user != null) {
                userData = UserData(
                    id = user!!._id,
                    username = user!!.username,
                    email = user!!.email,
                    password = user!!.password,
                    confirmPassword = user!!.confirmPassword,
                    user = user,
                )
            }
        }
        return userData

    }

    private fun closeRealmSync() {
        realm.close()
    }

    override suspend fun getUser(
        userName: String,
        email: String,
        password: String,
        confirmPassword: String
    ): UserData? {
        if (!this::realm.isInitialized) {
            setupRealmSync()
        }
        val user: User? = realm.query<User>(User::class, "username = \"$userName\"").first().find()
        return convertToUserData(user)
    }

    override suspend fun addUser(userData: UserData): UserData? {
        TODO("Not yet implemented")
    }

    override suspend fun deleteOneUser(userName: String): UserData? {
        TODO("Not yet implemented")
    }

    override suspend fun updateUser(
        userName: String,
        password: String,
        confirmPassword: String
    ): UserData? {
        TODO("Not yet implemented")
    }

    override suspend fun getAllUsers(userName: String): UserData? {
        TODO("Not yet implemented")
    }

}