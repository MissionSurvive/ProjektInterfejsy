package com.example.foodapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class AppScreens (
    val route: String
) {
    object StartScreen : AppScreens(
        route = "start",
    )

    object LoginScreen : AppScreens(
        route = "login",
    )

    object RegisterScreen : AppScreens(
        route = "register",
    )
}

