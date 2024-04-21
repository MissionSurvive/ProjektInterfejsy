package com.example.foodapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class AdminScreen (
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : AdminScreen(
        route = "home",
        title = "Główna",
        icon = Icons.Default.Home
    )

    object Add : AdminScreen(
        route = "add",
        title = "Dodaj",
        icon = Icons.Default.Add
    )

    object Account : AdminScreen(
        route = "account",
        title = "Konto",
        icon = Icons.Default.Person
    )
}