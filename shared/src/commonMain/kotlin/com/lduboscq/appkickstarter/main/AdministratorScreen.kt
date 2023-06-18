package com.lduboscq.appkickstarter.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.lduboscq.appkickstarter.ui.Image

class AdministratorScreen : Screen {

    @Composable
    override fun Content() {

        val screenModel =
            rememberScreenModel() { AdministratorScreenModel(RegisterRepositoryRealmLocal()) }
        val state by screenModel.state.collectAsState()
        val navigator = LocalNavigator.currentOrThrow
        var userName by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var confirmPassword by remember { mutableStateOf("") }


        Box(modifier = Modifier.fillMaxSize()) {
            Scaffold(
                bottomBar = { MyBottomBar() }
            ) {
                Image(
                    url = "https://i.pinimg.com/564x/73/93/0d/73930d5fa877f15da2139d9075d8013e.jpg",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize()
                )

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    when (val result = state) {
                        is AdministratorScreenModel.State.Init -> Text("Initialize.....")
                        is AdministratorScreenModel.State.Loading -> Text("Loading")
                        is AdministratorScreenModel.State.Result -> {
                            Text("Success")
                        }
                    }

                    TextField(
                        value = userName,
                        onValueChange = { userName = it },
                        textStyle = TextStyle(textAlign = TextAlign.Center),
                        label = { Text("Enter username to do CRUD") },
                        singleLine = true
                    )


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(onClick = {
                            screenModel.getUser(
                                userName,
                                email,
                                password,
                                confirmPassword
                            )
                        },
                            modifier = Modifier.padding(10.dp),
                            colors = ButtonDefaults.buttonColors(MaterialTheme.colors.secondary)) {
                            Text("Get User")
                        }

                        Button(
                            onClick = { screenModel.deleteUser(userName) },
                            modifier = Modifier.padding(10.dp),
                            colors = ButtonDefaults.buttonColors(MaterialTheme.colors.secondary)
                        ) {
                            Text("delete")
                        }

                        Button(onClick = {
                            screenModel.updatePassword(
                                userName,
                                password,
                                confirmPassword
                            )
                        }, modifier = Modifier.padding(10.dp),
                            colors = ButtonDefaults.buttonColors(MaterialTheme.colors.secondary)) {
                            Text("Update")
                        }


                        if (state is AdministratorScreenModel.State.Result.SingleResult) {
                            val userData =
                                (state as AdministratorScreenModel.State.Result.SingleResult).userData
                            Text("The results of the action are:")
                            if (userData != null) {
                                UserCard(userData = userData)
                            } else {
                                Text("No user data available.")
                            }
                        }
                    }

                }
            }
        }
    }
}