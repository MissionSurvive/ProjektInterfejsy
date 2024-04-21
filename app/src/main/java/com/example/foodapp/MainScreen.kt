package com.example.foodapp

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController = navController)}
    ) {
        BottomNavGraph(navController = navController)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {

    val statusBarLight = Color.WHITE
    val statusBarDark = Color.BLACK
    val navigationBarLight = Color.WHITE
    val navigationBarDark = Color.BLACK
    val view = LocalView.current
    val isDarkMod = isSystemInDarkTheme()

    DisposableEffect(isDarkMod) {
        val activity = view.context as Activity
        activity.window.statusBarColor = if(isDarkMod){statusBarDark} else {statusBarLight}
        activity.window.navigationBarColor = if(isDarkMod){navigationBarDark} else {navigationBarLight}

        WindowCompat.getInsetsController(activity.window, activity.window.decorView).apply {
            isAppearanceLightStatusBars = !isDarkMod
            isAppearanceLightNavigationBars = !isDarkMod
        }

        onDispose { }
    }

    val screens = listOf(
        AdminScreen.Home,
        AdminScreen.Add,
        AdminScreen.Account
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        screens.forEachIndexed { index, screen ->
            NavigationBarItem(
                icon = { Icon(
                    imageVector = screen.icon,
                    contentDescription = "Navigation Icon"
                ) },
                label = {Text(text = screen.title) },
                selected = currentDestination?.hierarchy?.any {
                    it.route == screen.route
                } == true,
                //unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true

                    }
                }
            )
        }
    }
}

/*@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}*/