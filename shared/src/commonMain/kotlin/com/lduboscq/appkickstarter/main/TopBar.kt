package com.lduboscq.appkickstarter.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

@Composable
fun TopBar(){

    val navigator = LocalNavigator.currentOrThrow
    TopAppBar(
    modifier = Modifier.fillMaxWidth(),
    backgroundColor = MaterialTheme.colors.secondary
    ) {

    }
}