package com.lduboscq.appkickstarter.main

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.RealmUUID
import io.realm.kotlin.types.annotations.PrimaryKey

class LoginUser: RealmObject {
    @PrimaryKey
    var _id: String = RealmUUID.random().toString()
    var email: String = ""
    var password: String = ""

}