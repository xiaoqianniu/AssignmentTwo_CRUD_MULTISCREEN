package com.lduboscq.appkickstarter.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.lduboscq.appkickstarter.main.ui.theme.md_theme_dark_primary

@Composable
fun TopBar(){

    val navigator = LocalNavigator.currentOrThrow
    TopAppBar(
    modifier = Modifier.fillMaxWidth(),
    backgroundColor = md_theme_dark_primary
    ) {

    }
}