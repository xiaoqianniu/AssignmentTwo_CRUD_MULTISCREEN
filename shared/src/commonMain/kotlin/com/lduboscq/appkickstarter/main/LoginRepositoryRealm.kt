package com.lduboscq.appkickstarter.main

import io.realm.kotlin.Realm

abstract class LoginRepositoryRealm : UserRepository {

    lateinit var realm: Realm

    abstract suspend fun setupRealmSync()

    suspend fun convertToLoginUserData(user: User?): LoginUserData? {
        if (!this::realm.isInitialized) {
            setupRealmSync()
        }

        var loginUserData: LoginUserData? = null
        realm.write {
            if (user != null) {
                loginUserData = LoginUserData(
                    id = user!!._id,
                    email = user!!.email,
                    password = user!!.password,
                    user = user,
                )
            }
        }
        return loginUserData

    }

    private fun closeRealmSync() {
        realm.close()
    }

    override suspend fun getUser(
        email: String,
        password: String,
    ): LoginUserData? {
        if (!this::realm.isInitialized) {
            setupRealmSync()
        }
        val user: User? = realm.query<User>(User::class, "email = \"$email\"").first().find()
        return convertToLoginUserData(user)
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

    override suspend fun getAllUsers(): List<UserData> {
        TODO("Not yet implemented")
    }


}