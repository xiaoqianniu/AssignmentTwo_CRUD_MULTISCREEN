package com.lduboscq.appkickstarter.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.lduboscq.appkickstarter.main.ui.theme.md_theme_dark_primary
import com.lduboscq.appkickstarter.main.ui.theme.md_theme_light_secondary
import com.lduboscq.appkickstarter.main.ui.theme.md_theme_light_tertiaryContainer
import com.lduboscq.appkickstarter.ui.Image
// Renders the user interface for the administrator screen
// and manages the state using the AdministratorScreenModel.
// Handles user interactions and updates the UI based on the state.
// Performs CRUD operations on user data and displays the results.
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

        // The main content of the screen wrapped in a Box
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
                            colors = ButtonDefaults.buttonColors(md_theme_dark_primary)) {
                            Text("GetOne")
                        }

                        Button(
                            onClick = { screenModel.deleteUser(userName) },
                            modifier = Modifier.padding(10.dp),
                            colors = ButtonDefaults.buttonColors(md_theme_dark_primary)
                        ) {
                            Text("delete")
                        }

                        Button(onClick = {
                            screenModel.getAll()
                        }, modifier = Modifier.padding(10.dp),
                            colors = ButtonDefaults.buttonColors(md_theme_dark_primary )) {
                            Text("getAll")
                        }
                    }
                    if (state is AdministratorScreenModel.State.Result.SingleResult) {
                        val userData = (state as AdministratorScreenModel.State.Result.SingleResult).userData
                        Text("The result of the action is:")
                        if (userData != null) {
                            UserCard(userData = userData,model = screenModel)
                        } else {
                            Text("No user data available.")
                        }
                    } else if (state is AdministratorScreenModel.State.Result.MultipleResult) {
                        val listUserData = (state as AdministratorScreenModel.State.Result.MultipleResult).userDatas
                        Text("The results of the action are:")
                        if (listUserData != null) {
                            LazyColumn {
                                items(listUserData) { userData ->
                                    UserCard(userData = userData,model = screenModel)
                                }
                            }
                        } else {
                            Text("No user data available.")
                        }
                    }

                }
            }
        }
    }
}