package com.example.foodapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.foodapp.AdminScreens.AccountScreen
import com.example.foodapp.AdminScreens.AddScreen
import com.example.foodapp.AdminScreens.HomeScreen

@Composable
fun AdminBottomNavGraph(navController: NavHostController, appNavController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AdminPanel.Home.route
    ) {
        composable(route = AdminPanel.Home.route) {
            HomeScreen(context = LocalContext.current)
        }
        composable(route = AdminPanel.Add.route) {
            AddScreen(context = LocalContext.current, navController)
        }
        composable(route = AdminPanel.Account.route) {
            AccountScreen(navController = navController, appNavController = appNavController)
        }
    }
}