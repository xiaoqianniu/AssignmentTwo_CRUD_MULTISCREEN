package com.lduboscq.appkickstarter.main

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

class LoginRepositoryRealmLocal: LoginRepositoryRealm() {
    override suspend fun setupRealmSync() {

            val config = RealmConfiguration.Builder(setOf(User::class))
                .build()
            realm = Realm.open(config)

    }
}