package com.example.foodapp

import android.app.Activity
import android.graphics.Color
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlin.system.exitProcess

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen(navController: NavController,
                backPressedDispatcher: OnBackPressedDispatcherOwner? = LocalOnBackPressedDispatcherOwner.current) {
    val showExitDialog = remember { mutableStateOf(false) }

    if (showExitDialog.value) {
        AlertDialog(
            onDismissRequest = { showExitDialog.value = false },
            title = { Text("Opuszczanie aplikacji") },
            text = { Text("Czy na pewno chcesz opuścić aplikację?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        exitProcess(0)
                    }
                ) {
                    Text("Tak")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showExitDialog.value = false }
                ) {
                    Text("Nie")
                }
            }
        )
    }
    BackPressHandler {
        showExitDialog.value = true;
    }
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
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        val image: Painter = painterResource(id = R.drawable.salad)
                        Image(
                            modifier = Modifier
                                .size(270.dp)
                                .clip(RoundedCornerShape(16.dp)),
                            painter = image,
                            contentDescription = "",
                            contentScale = ContentScale.Crop
                        )
                        Column(
                            modifier = Modifier
                                .height(40.dp)
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Witamy w FoodDroid!",
                                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                            )
                        }
                        Column(
                            modifier = Modifier
                                .height(40.dp)
                                .width(270.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Zaloguj się lub utwórz konto aby rozpocząć zamawianie pysznego jedzenia.",
                                fontSize = MaterialTheme.typography.labelMedium.fontSize,
                                textAlign = TextAlign.Center
                            )
                        }
                        Surface(
                            modifier = Modifier
                                .height(60.dp)
                                .width(270.dp)
                        ) {
                            Button(
                                onClick = { navController.navigate(AppScreens.LoginScreen.route) }) {
                                Text("Zaloguj się")
                            }
                        }
                        Surface(
                            modifier = Modifier
                                .height(10.dp)
                                .fillMaxWidth()
                        ) {}
                        Surface(
                            modifier = Modifier
                                .height(60.dp)
                                .width(270.dp)
                        ) {
                            Button(
                                onClick = { navController.navigate(AppScreens.RegisterScreen.route) }) {
                                Text("Zarejestruj się")
                            }
                        }
                    }
                }
            }
}

@Composable
@Preview(showBackground = true)
fun StartScreenPreview() {
    StartScreen(navController = NavController(LocalContext.current));
}