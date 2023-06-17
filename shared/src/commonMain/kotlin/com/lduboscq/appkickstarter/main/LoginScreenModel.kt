package com.lduboscq.appkickstarter.main

import cafe.adriel.voyager.core.model.StateScreenModel
import kotlinx.coroutines.flow.Flow

class LoginScreenModel(private val repository: LoginRepositoryRealm) :

StateScreenModel<LoginScreenModel.State>(State.Init)
{

    sealed class State {
        object Init : State()
        object Loading : State()
        sealed class Result : State() {
            class SingleResult(val userData: UserData?) : Result()
            class MultipleResult(val userDatas: Flow<UserData>?) : Result()
        }
    }


}