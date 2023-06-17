package com.lduboscq.appkickstarter

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.lduboscq.appkickstarter.list.ListScreenContent
import com.lduboscq.appkickstarter.list.PersonsListScreen
import com.lduboscq.appkickstarter.main.RegisterScreen
import com.lduboscq.appkickstarter.main.ui.theme.AppTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
public fun MainApp() {
    AppTheme {
        Navigator(RegisterScreen()) { navigator ->
            SlideTransition(navigator)
        }
    }
}
