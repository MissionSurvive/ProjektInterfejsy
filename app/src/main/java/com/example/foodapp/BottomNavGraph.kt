package com.example.foodapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.foodapp.AdminScreens.AccountScreen
import com.example.foodapp.AdminScreens.AddScreen
import com.example.foodapp.AdminScreens.HomeScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AdminScreen.Home.route
    ) {
        composable(route = AdminScreen.Home.route) {
            HomeScreen(context = LocalContext.current)
        }
        composable(route = AdminScreen.Add.route) {
            AddScreen(context = LocalContext.current)
        }
        composable(route = AdminScreen.Account.route) {
            AccountScreen()
        }
    }
}