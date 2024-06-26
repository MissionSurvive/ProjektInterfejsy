package com.example.foodapp.Panels

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
//import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ClientPanel (
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Menu : ClientPanel(
        route = "menu",
        title = "Menu",
        icon = Icons.Default.Home
    )

    object Cart : ClientPanel(
        route = "cart",
        title = "Koszyk",
        icon = Icons.Default.ShoppingCart
    )

    object ClientAccount : ClientPanel(
        route = "account",
        title = "Konto",
        icon = Icons.Default.Person
    )
}