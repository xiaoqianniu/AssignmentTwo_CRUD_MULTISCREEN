package com.lduboscq.appkickstarter.main

import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class LoginScreenModel(private val repository: LoginRepositoryRealm) :

StateScreenModel<LoginScreenModel.State>(State.Init)
{

    sealed class State {
        object Init : State()
        object Loading : State()
        sealed class Result : State() {
            class SingleResult(val loginUserData: LoginUserData?) : Result()
            class MultipleResult(val loginUserDatas: Flow<LoginUserData>?) : Result()
        }
    }
    fun login(email: String, password: String) {
        coroutineScope.launch {
            mutableState.value = State.Loading
            val loginUserData = repository.getUser(email,password)
            mutableState.value = State.Result.SingleResult(loginUserData)
        }
    }

}