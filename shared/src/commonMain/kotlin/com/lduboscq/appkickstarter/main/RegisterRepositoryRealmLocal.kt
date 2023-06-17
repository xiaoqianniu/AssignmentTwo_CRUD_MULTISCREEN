package com.lduboscq.appkickstarter.main

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

class RegisterRepositoryRealmLocal: RegisterRepositoryRealm() {
    override suspend fun setupRealmSync() {
        val config = RealmConfiguration.Builder(setOf(User::class))
            .build()
        realm = Realm.open(config)
    }

    override suspend fun getAllUsers(userName: String): UserData? {
        TODO("Not yet implemented")
    }
}