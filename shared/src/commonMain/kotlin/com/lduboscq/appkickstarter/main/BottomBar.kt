package com.lduboscq.appkickstarter.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow


@Composable
fun MyBottomBar() {
    val navigator = LocalNavigator.currentOrThrow
    BottomAppBar(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.secondary
    ) {

        Text(
            "Login", modifier = Modifier.clickable(onClick = {
                navigator.push(ScreenRouter(AllScreens.Login))
            }).padding(horizontal = 15.dp), fontSize = 20.sp, fontStyle = FontStyle.Normal
        )

        Spacer(modifier = Modifier.width(15.dp))

        Text(
            "Register", modifier = Modifier.clickable(onClick = {
                navigator.push(ScreenRouter(AllScreens.Register))
            }), fontSize = 20.sp, fontStyle = FontStyle.Normal
        )
        Spacer(modifier = Modifier.width(15.dp))
        Text(
            "Profile", modifier = Modifier.clickable(onClick = {
                navigator.push(ScreenRouter(AllScreens.Profile(email = null)))
            }).padding(horizontal = 15.dp), fontSize = 20.sp, fontStyle = FontStyle.Normal
        )

    }
}
