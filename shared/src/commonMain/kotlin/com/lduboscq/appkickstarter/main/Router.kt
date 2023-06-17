package com.lduboscq.appkickstarter.main

import cafe.adriel.voyager.core.screen.Screen

sealed class AllScreens {

    object Login : AllScreens()

    object Register : AllScreens()


}

fun ScreenRouter(screen: AllScreens): Screen {
    return when (screen) {

        is AllScreens.Login ->
            LoginScreen()


        is AllScreens.Register ->
            RegisterScreen()


    }
}