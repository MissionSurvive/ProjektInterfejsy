package com.example.foodapp.ClientScreens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.foodapp.AppScreens
import com.example.foodapp.ClientPanel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientAccountScreen(navController: NavHostController, appNavController: NavHostController, bottomBarState: MutableState<Boolean>) {
    bottomBarState.value = true
    Scaffold (
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(ClientPanel.Menu.route) }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Go back"
                        )
                    }
                },
                title = {
                    Text(text = "Konto")
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
                )
            )
        },
        content = {innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Surface(
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp, vertical = 10.dp)
                ) {
                    Text(
                        text = "Zalogowany jako",
                        fontSize = MaterialTheme.typography.titleLarge.fontSize
                    )
                }
                Surface(
                    modifier = Modifier
                        .height(30.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                ) {
                    Text(
                        text = "<user_name>",
                        fontSize = MaterialTheme.typography.labelLarge.fontSize
                    ) }
                Surface(
                    modifier = Modifier
                        .height(30.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                ) {
                    Text(
                        text = "<phone>",
                        fontSize = MaterialTheme.typography.labelLarge.fontSize
                    ) }
                Surface(
                    modifier = Modifier
                        .height(30.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                ) {
                    Text(
                        text = "<e-mail>",
                        fontSize = MaterialTheme.typography.labelLarge.fontSize
                    ) }
                Surface(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                ) {
                    Text(
                        text = "Twój adres",
                        fontSize = MaterialTheme.typography.titleLarge.fontSize
                    )
                }
                Surface(
                    modifier = Modifier
                        .height(30.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                ) {
                    Text(
                        text = "<street_name>",
                        fontSize = MaterialTheme.typography.labelLarge.fontSize
                    ) }
                Surface(
                    modifier = Modifier
                        .height(30.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                ) {
                    Text(
                        text = "<home_number>",
                        fontSize = MaterialTheme.typography.labelLarge.fontSize
                    ) }
                Surface(
                    modifier = Modifier
                        .height(30.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                ) {
                    Text(
                        text = "<city>",
                        fontSize = MaterialTheme.typography.labelLarge.fontSize
                    ) }
                Surface(
                    modifier = Modifier
                        .height(230.dp)
                        .fillMaxWidth()
                ) {}
                Surface(
                    modifier = Modifier
                        .height(60.dp)
                        .width(270.dp)
                ) {
                    Button(
                        onClick = { appNavController.navigate(AppScreens.StartScreen.route) {
                            popUpTo(appNavController.graph.findStartDestination().id)
                            launchSingleTop = true
                        } }) {
                        Text("Wyloguj")
                    } }
            }
        }
    )
}

@Composable
@Preview
fun AccountScreenPreview() {
    //ClientAccountScreen();
}