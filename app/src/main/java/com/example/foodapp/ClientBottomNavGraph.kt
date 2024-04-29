package com.example.foodapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.foodapp.ClientScreens.CartScreen
import com.example.foodapp.ClientScreens.ClientAccountScreen
import com.example.foodapp.ClientScreens.MenuScreen

@Composable
fun ClientBottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = ClientPanel.Menu.route
    ) {
        composable(route = ClientPanel.Menu.route) {
            MenuScreen(context = LocalContext.current)
        }
        composable(route = ClientPanel.Cart.route) {
            CartScreen(context = LocalContext.current, navController)
        }
        composable(route = ClientPanel.ClientAccount.route) {
            ClientAccountScreen(navController)
        }
    }
}