package com.lduboscq.appkickstarter.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.lduboscq.appkickstarter.main.ui.theme.md_theme_dark_onPrimary
import com.lduboscq.appkickstarter.main.ui.theme.md_theme_dark_primary
import com.lduboscq.appkickstarter.main.ui.theme.md_theme_light_secondary
import com.lduboscq.appkickstarter.ui.Image
import kotlinx.coroutines.runBlocking


/**
 * Represents the login screen.
 */
class LoginScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel =
            rememberScreenModel() { LoginScreenModel(LoginRepositoryRealmLocal()) }
        val state by screenModel.state.collectAsState()
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var isClicked by remember { mutableStateOf(true) }
        val navigator = LocalNavigator.currentOrThrow
        var errorMessage by remember { mutableStateOf("") }

        Box(modifier = Modifier.fillMaxSize()) {
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
                    is LoginScreenModel.State.Init -> Text("...")
                    is LoginScreenModel.State.Loading -> Text("Loading")
                    is LoginScreenModel.State.Result -> { Text("Success") }
                    else -> {Text("Invalid email or password", color = Color.Red)}
                }
                Text(
                    "LoginMe",
                    color = MaterialTheme.colors.onSecondary,
                    fontSize = 30.sp,
                    style = MaterialTheme.typography.h3,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 50.dp)
                )

                Card( modifier = Modifier.padding(horizontal = 30.dp, vertical = 10.dp),
                    elevation = 10.dp) {
                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        textStyle = TextStyle(textAlign = TextAlign.Center),
                        label = { Text("Enter email address") },
                        leadingIcon = {
                            Icon(Icons.Filled.Email, contentDescription = "email icon")
                        },
                        singleLine = true
                    )
                }

                Card( modifier = Modifier.padding(horizontal = 30.dp, vertical = 10.dp),
                    elevation = 10.dp) {
                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        textStyle = TextStyle(textAlign = TextAlign.Center),
                        label = { Text("Enter password") },
                        leadingIcon = {
                            Icon(Icons.Filled.Lock, contentDescription = "password icon")
                        },
                        visualTransformation = PasswordVisualTransformation(),
                        singleLine = true
                    )
                }

                Text(
                    "New user? Click here",
                    modifier = Modifier.padding(10.dp)
                        .clickable(onClick = { navigator.push(ScreenRouter(AllScreens.Register)) }),
                    color = md_theme_light_secondary,
                    textDecoration = TextDecoration.Underline,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )

                Button(
                    onClick = {   val result = runBlocking {
                       screenModel.login(email, password)
                    }
                        when (result) {
                            is LoginScreenModel.LoginResult.Success -> {
                                // Navigate to profile screen
                                navigator.push(ScreenRouter(AllScreens.Profile(email)))
                            }
                            is LoginScreenModel.LoginResult.Error -> {
                                // Show error message
                                errorMessage = "Invalid credentials"
                            }
                        }

                    }, modifier = Modifier.padding(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        md_theme_dark_primary
                    ),

                    enabled = !email.isEmpty() && !password.isEmpty()
                ) {

                    Text("Login")
                }
            }
        }

    }
}

/**
 * Changes the color based on whether the button is clicked or not.
 *
 * @param isClicked Determines whether the button is clicked or not.
 * @return The color to be used for the text.
 */
@Composable
fun colorChangeByClick(isClicked: Boolean): Color {

    return if (isClicked) {
        MaterialTheme.colors.onSecondary
    } else {
        MaterialTheme.colors.error
    }
}