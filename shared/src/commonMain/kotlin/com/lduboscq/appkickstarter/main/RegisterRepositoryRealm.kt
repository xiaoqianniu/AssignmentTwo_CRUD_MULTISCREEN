package com.lduboscq.appkickstarter.main

import io.realm.kotlin.Realm
import io.realm.kotlin.types.RealmUUID

abstract class RegisterRepositoryRealm:UserRepository {

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
        if (!this::realm.isInitialized) {
            setupRealmSync()
        }
        var newUser: User? = null
        realm.write {
            newUser = this.copyToRealm(User().apply {
                _id = userData.id ?: RealmUUID.random().toString()
                username = userData.username
                email = userData.email
                password = userData.password
                confirmPassword = userData.confirmPassword
            })
        }

        return convertToUserData(newUser)
    }

    override suspend fun updateUser(userName: String,password: String,confirmPassword: String): UserData? {
        if (!this::realm.isInitialized) {
            setupRealmSync()
        }
        var userData: UserData? = null


        try {
            val findUser: User? =
                realm.query<User>(User::class, "username = \"$userName\"").first().find()
            //update one object asynchronously
            realm.write {
                if (findUser != null) {
                    findLatest(findUser)!!.password = password
                    findLatest(findUser)!!.confirmPassword = confirmPassword
                }
            }
            userData = convertToUserData(findUser)
        } catch (e: Exception) {
            print(e.message)
        }
        return userData
    }
    override suspend fun deleteOneUser(userName: String): UserData? {
        if (!this::realm.isInitialized) {
            setupRealmSync()
        }
        var user2: UserData? = null


        try {
            val findUser: User? =
                realm.query<User>(User::class, "username = \"$userName\"").first().find()
            realm.write {
                if (findUser != null) {
                    user2 = UserData(
                        id = findLatest(findUser)!!._id,
                        username = findLatest(findUser)!!.username,
                        password = findLatest(findUser)!!.password,
                        confirmPassword = findLatest(findUser)!!.confirmPassword,
                        user = null
                    )
                    findLatest(findUser)
                        ?.also { delete(it) }
                }

            }
        } catch (e: Exception) {
            print(e.message)
        }
        return user2

    }

}