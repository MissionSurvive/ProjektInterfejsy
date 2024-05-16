package com.example.foodapp.AppScreens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.foodapp.BackPressHandler
import com.example.foodapp.Panels.AppPanel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(context: Context, navController: NavController) {
    BackPressHandler {
        navController.navigate(AppPanel.StartScreen.route)
    }
    val state = rememberScrollState()
    LaunchedEffect(Unit) { state.animateScrollTo(0) }
    Scaffold (
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(AppPanel.StartScreen.route) }) {
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
                    .padding(innerPadding)
                    .verticalScroll(state),
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
                        var text by remember { mutableStateOf("") }
                        OutlinedTextField(
                            value = text,
                            onValueChange = { newText ->
                                text = newText
                            },
                            label = {
                                Text("Imię")
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next
                            ),
                            trailingIcon = {
                                IconButton(onClick = { text = "" }) {
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
                        var text by remember { mutableStateOf("") }
                        OutlinedTextField(
                            value = text,
                            onValueChange = { newText ->
                                text = newText
                            },
                            label = {
                                Text("Nazwisko")
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next,
                            ),
                            trailingIcon = {
                                IconButton(onClick = { text = "" }) {
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
                        var text by remember { mutableStateOf("") }
                        OutlinedTextField(
                            value = text,
                            onValueChange = { newText ->
                                text = newText
                            },
                            label = {
                                Text("Telefon")
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next,
                            ),
                            trailingIcon = {
                                IconButton(onClick = { text = "" }) {
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
                        var text by remember { mutableStateOf("") }
                        OutlinedTextField(
                            value = text,
                            onValueChange = { newText ->
                                text = newText
                            },
                            label = {
                                Text("Ulica")
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next,
                            ),
                            trailingIcon = {
                                IconButton(onClick = { text = "" }) {
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
                        var text by remember { mutableStateOf("") }
                        OutlinedTextField(
                            value = text,
                            onValueChange = { newText ->
                                text = newText
                            },
                            label = {
                                Text("Nr budynku")
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next,
                            ),
                            trailingIcon = {
                                IconButton(onClick = { text = "" }) {
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
                        var text by remember { mutableStateOf("") }
                        OutlinedTextField(
                            value = text,
                            onValueChange = { newText ->
                                text = newText
                            },
                            label = {
                                Text("Nr mieszkania")
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next,
                            ),
                            trailingIcon = {
                                IconButton(onClick = { text = "" }) {
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
                        var text by remember { mutableStateOf("") }
                        OutlinedTextField(
                            value = text,
                            onValueChange = { newText ->
                                text = newText
                            },
                            label = {
                                Text("Miasto")
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next,
                            ),
                            trailingIcon = {
                                IconButton(onClick = { text = "" }) {
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
                        var text by remember { mutableStateOf("") }
                        OutlinedTextField(
                            value = text,
                            onValueChange = { newText ->
                                text = newText
                            },
                            label = {
                                Text("E-mail")
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Email,
                                imeAction = ImeAction.Next,
                            ),
                            trailingIcon = {
                                IconButton(onClick = { text = "" }) {
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
                        var text by remember { mutableStateOf("") }
                        OutlinedTextField(
                            value = text,
                            onValueChange = { newText ->
                                text = newText
                            },
                            label = {
                                Text("Hasło")
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Done,
                            ),
                            trailingIcon = {
                                IconButton(onClick = { text = "" }) {
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
                            onClick = { Toast.makeText(context, "Rejestracja do implementacji!", Toast.LENGTH_SHORT).show() }) {
                            Text("Zarejestruj")
                        }
                    }
                    Surface(
                        modifier = Modifier
                            .height(10.dp)
                            .fillMaxWidth()
                    ) {}
                }
            }
        })
}

@Composable
@Preview
fun RegisterScreenPreview() {
    //RegisterScreen();
}