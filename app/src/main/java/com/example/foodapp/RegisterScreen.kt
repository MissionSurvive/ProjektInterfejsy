package com.example.foodapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController) {
    BackPressHandler {
        navController.navigate(AppScreens.StartScreen.route)
    }
    Scaffold (
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(AppScreens.StartScreen.route) }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Go back"
                        )
                    }
                },
                title = {
                    Text(text = "Rejestracja")
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
                )
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Column(
                        modifier = Modifier
                            .padding(10.dp)
                            .width(270.dp),
                    ) {
                        var text by remember { mutableStateOf("<e-mail>") }
                        OutlinedTextField(
                            value = text,
                            onValueChange = { newText ->
                                text = newText
                            },
                            label = {
                                Text("E-mail")
                            },
                            singleLine = true,
                            trailingIcon = {
                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(
                                        imageVector = Icons.Default.Clear,
                                        contentDescription = "Clear button"
                                    )
                                }
                            })
                    }
                    Column(
                        modifier = Modifier
                            .padding(10.dp)
                            .width(270.dp),
                    ) {
                        var text by remember { mutableStateOf("<password>") }
                        OutlinedTextField(
                            value = text,
                            onValueChange = { newText ->
                                text = newText
                            },
                            label = {
                                Text("Hasło")
                            },
                            singleLine = true,
                            trailingIcon = {
                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(
                                        imageVector = Icons.Default.Clear,
                                        contentDescription = "Clear button"
                                    )
                                }
                            })
                    }
                    Surface(
                        modifier = Modifier
                            .height(60.dp)
                            .width(270.dp)
                    ) {
                        Button(
                            onClick = { /*TODO*/ }) {
                            Text("Zarejestruj")
                        }
                    }
                }
            }
        })
}

@Composable
@Preview
fun RegisterScreenPreview() {
    //RegisterScreen();
}