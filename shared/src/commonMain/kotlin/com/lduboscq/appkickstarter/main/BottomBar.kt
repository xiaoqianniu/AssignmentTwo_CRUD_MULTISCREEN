package com.lduboscq.appkickstarter.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.lduboscq.appkickstarter.main.ui.theme.md_theme_dark_onPrimary
import com.lduboscq.appkickstarter.main.ui.theme.md_theme_dark_primary


@Composable
fun MyBottomBar() {
    val navigator = LocalNavigator.currentOrThrow
    BottomAppBar(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = md_theme_dark_primary
    ) {
        // "Login" button
        TextButton(onClick = {
            navigator.push(ScreenRouter(AllScreens.Login))
        }) {
            Text(
                text = "Login",
                modifier = Modifier.padding(horizontal = 10.dp),
                fontSize = 15.sp,
                fontStyle = FontStyle.Normal,
                color = md_theme_dark_onPrimary
            )
        }

       // "Register" button
        TextButton(onClick = {
            navigator.push(ScreenRouter(AllScreens.Register))
        }) {
            Text(
                text = "Register",
                modifier = Modifier.padding(horizontal = 10.dp),
                fontSize = 15.sp,
                fontStyle = FontStyle.Normal,
                color = md_theme_dark_onPrimary
            )
        }

        // "Profile" button
        TextButton(onClick = {
            navigator.push(ScreenRouter(AllScreens.Profile(email = null)))
        }) {
            Text(
                text = "Profile",
                modifier = Modifier.padding(horizontal = 10.dp),
                fontSize = 15.sp,
                fontStyle = FontStyle.Normal,
                color = md_theme_dark_onPrimary
            )
        }
        // "AboutUs" button
        TextButton(onClick = {
            navigator.push(ScreenRouter(AllScreens.AboutUs))
        }) {
            Text(
                text = "AboutUs",
                modifier = Modifier.padding(horizontal =10.dp),
                fontSize = 15.sp,
                fontStyle = FontStyle.Normal,
                color = md_theme_dark_onPrimary
            )
        }
    }
}
