package com.example.foodapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.foodapp.AdminScreens.DetailedFoodPropertiesScreen
import com.example.foodapp.ClientScreens.CartScreen
import com.example.foodapp.ClientScreens.ClientAccountScreen
import com.example.foodapp.ClientScreens.DetailedFoodOrderScreen
import com.example.foodapp.ClientScreens.MenuScreen

@Composable
fun ClientBottomNavGraph(navController: NavHostController, appNavController: NavHostController, bottomBarState: MutableState<Boolean>) {
    NavHost(
        navController = navController,
        startDestination = ClientPanel.Menu.route
    ) {
        composable(route = ClientPanel.Menu.route) {
            MenuScreen(context = LocalContext.current, bottomBarState)
        }
        composable(route = ClientPanel.Cart.route) {
            CartScreen(context = LocalContext.current, navController, bottomBarState)
        }
        composable(route = ClientPanel.ClientAccount.route) {
            ClientAccountScreen(navController = navController, appNavController = appNavController, bottomBarState)
        }
        composable("DetailedFoodOrder/{foodId}") { backStackEntry ->
            val foodId = backStackEntry.arguments?.getString("foodId")
            if(foodId != null) {
                bottomBarState.value = false // Hide the bottom bar
                DetailedFoodOrderScreen(
                    context = LocalContext.current,
                    foodId = foodId,
                    navController = navController,
                    bottomBarState = bottomBarState
                )
            }
        }
    }
}