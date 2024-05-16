package com.example.foodapp.NavigationGraphs

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodapp.Panels.AdminPanel
import com.example.foodapp.AppScreens.AdminScreen
import com.example.foodapp.Panels.AppPanel
import com.example.foodapp.Panels.ClientPanel
import com.example.foodapp.AppScreens.ClientScreen
import com.example.foodapp.AppScreens.LoginScreen
import com.example.foodapp.AppScreens.RegisterScreen
import com.example.foodapp.AppScreens.StartScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppPanel.StartScreen.route
    ) {
        composable(route = AppPanel.StartScreen.route) {
            StartScreen(navController = navController)
        }
        composable(route = AppPanel.LoginScreen.route) {
            LoginScreen(LocalContext.current, navController = navController)
        }
        composable(route = AppPanel.RegisterScreen.route) {
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