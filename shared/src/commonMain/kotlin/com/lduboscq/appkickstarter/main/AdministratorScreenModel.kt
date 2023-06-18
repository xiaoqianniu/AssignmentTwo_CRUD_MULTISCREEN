package com.lduboscq.appkickstarter.main

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class AdministratorScreenModel(private val repository: RegisterRepositoryRealm) :

    StateScreenModel<AdministratorScreenModel.State>(AdministratorScreenModel.State.Init) {


    sealed class State {
        object Init : State()
        object Loading : State()
        sealed class Result : State() {
            class SingleResult(val userData: UserData?) : Result()
            class MultipleResult(val userDatas: Flow<UserData>?) : Result()
        }
    }
    fun getUser(userName: String, email: String, password: String, confirmPassword: String) {
        coroutineScope.launch {
            mutableState.value = AdministratorScreenModel.State.Loading
            mutableState.value = AdministratorScreenModel.State.Result.SingleResult(
                repository.getUser(
                    userName,
                    email,
                    password,
                    confirmPassword
                )
            )
        }

    }

    fun deleteUser(userName: String) {
        coroutineScope.launch {
            mutableState.value = AdministratorScreenModel.State.Loading

            mutableState.value = AdministratorScreenModel.State.Result.SingleResult(
                repository.deleteOneUser(userName)
            )
        }
    }

    fun updatePassword(userName: String, password: String, confirmPassword: String) {
        coroutineScope.launch {
            mutableState.value = AdministratorScreenModel.State.Loading

            mutableState.value = AdministratorScreenModel.State.Result.SingleResult(
                repository.updateUser(
                    userName,
                    password,
                    confirmPassword
                )
            )
        }
    }


}