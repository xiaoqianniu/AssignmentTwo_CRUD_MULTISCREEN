package com.lduboscq.appkickstarter.main

import cafe.adriel.voyager.core.screen.Screen

sealed class AllScreens {

    object Login : AllScreens()

    object Register : AllScreens()
    object Administrator : AllScreens()

    data class Profile(val email: String?) : AllScreens()


}

fun ScreenRouter(screen: AllScreens): Screen {
    return when (screen) {

        is AllScreens.Login ->
            LoginScreen()


        is AllScreens.Register ->
            RegisterScreen()

        is AllScreens.Administrator ->
            AdministratorScreen()

        is AllScreens.Profile ->
            ProfileScreen(screen.email?:"")
    }
}