package com.example.foodapp.NavigationGraphs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.foodapp.Panels.AdminPanel
import com.example.foodapp.AdminScreens.AccountScreen
import com.example.foodapp.AdminScreens.AddScreen
import com.example.foodapp.AdminScreens.DetailedFoodPropertiesScreen
import com.example.foodapp.AdminScreens.HomeScreen

@Composable
fun AdminBottomNavGraph(navController: NavHostController, appNavController: NavHostController, bottomBarState: MutableState<Boolean>) {
    NavHost(
        navController = navController,
        startDestination = AdminPanel.Home.route
    ) {
        composable(route = AdminPanel.Home.route) {
            HomeScreen(context = LocalContext.current, bottomBarState)
        }
        composable(route = AdminPanel.Add.route) {
            AddScreen(context = LocalContext.current, navController, bottomBarState)
        }
        composable(route = AdminPanel.Account.route) {
            AccountScreen(navController = navController, appNavController = appNavController, bottomBarState)
        }
        composable("DetailedFoodProperties/{foodId}") { backStackEntry ->
            val foodId = backStackEntry.arguments?.getString("foodId")
            if(foodId != null) {
                bottomBarState.value = false // Hide the bottom bar
                DetailedFoodPropertiesScreen(
                    context = LocalContext.current,
                    foodId = foodId,
                    navController = navController,
                    bottomBarState = bottomBarState
                )
            }
        }
    }
}