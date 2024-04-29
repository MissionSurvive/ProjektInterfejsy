package com.example.foodapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.StartScreen.route
    ) {
        composable(route = AppScreens.StartScreen.route) {
            StartScreen(navController = navController)
        }
        composable(route = AppScreens.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(route = AppScreens.RegisterScreen.route) {
            RegisterScreen(LocalContext.current, navController = navController)
        }
        composable(route = AdminPanel.Home.route) {
            AdminScreen()
        }
        composable(route = ClientPanel.Menu.route) {
            ClientScreen()
        }
    }
}