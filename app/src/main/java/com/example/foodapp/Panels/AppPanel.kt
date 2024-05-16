package com.example.foodapp.Panels

sealed class AppPanel (
    val route: String
) {
    object StartScreen : AppPanel(
        route = "start",
    )

    object LoginScreen : AppPanel(
        route = "login",
    )

    object RegisterScreen : AppPanel(
        route = "register",
    )
}

