package com.example.foodapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.ViewModelStore
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
            LoginScreen(LocalContext.current, navController = navController)
        }
        composable(route = AppScreens.RegisterScreen.route) {
            RegisterScreen(LocalContext.current, navController = navController)
        }
        composable(route = AdminPanel.Home.route) {
            AdminScreen(navController = navController)
        }
        composable(route = ClientPanel.Menu.route) {
            ClientScreen(navController = navController)
        }
    }
}