package com.lduboscq.appkickstarter.main

import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
/**
 * Represents the model for the login screen.
 *
 * @param repository The repository for login operations.
 */
class LoginScreenModel(private val repository: LoginRepositoryRealm) :

StateScreenModel<LoginScreenModel.State>(State.Init)
{
    /**
     * Represents the possible states of the login screen.
     */
    sealed class State {
        object Init : State()
        object Loading : State()
        sealed class Result : State() {
            class SingleResult(val loginUserData: LoginUserData?) : Result()
            class MultipleResult(val loginUserDatas: Flow<LoginUserData>?) : Result()
        }
    }

    /**
     * Performs the login operation.
     *
     * @param email The email address.
     * @param password The password.
     */
    fun login(email: String, password: String) {
        coroutineScope.launch {
            mutableState.value = State.Loading
            val loginUserData = repository.getUser(email,password)
            mutableState.value = State.Result.SingleResult(loginUserData)
        }
    }

}