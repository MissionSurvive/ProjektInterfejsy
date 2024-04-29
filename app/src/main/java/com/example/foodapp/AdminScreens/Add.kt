package com.example.foodapp.AdminScreens

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.foodapp.AdminPanel
import com.example.foodapp.DBHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.IOException
import java.lang.Exception

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(context: Context, navController: NavController) {

    val activity = context as Activity

    var foodName by remember { mutableStateOf("") }
    var smallPrice by remember { mutableStateOf("") }
    var smallKcal by remember { mutableStateOf("") }
    var smallPortion by remember { mutableStateOf("") }
    var mediumPrice by remember { mutableStateOf("") }
    var mediumKcal by remember { mutableStateOf("") }
    var mediumPortion by remember { mutableStateOf("") }
    var bigPrice by remember { mutableStateOf("") }
    var bigKcal by remember { mutableStateOf("") }
    var bigPortion by remember { mutableStateOf("") }
    var isKetchup by remember { mutableStateOf(false) }
    var isGarlic by remember { mutableStateOf(false) }
    var isExpanded by remember { mutableStateOf(false) }
    var foodCategory by remember { mutableStateOf("Burgery") }

    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    var selectedImageByteArray by remember {
        mutableStateOf<ByteArray?>(null)
    }

    var photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {uri ->
            selectedImageUri = uri
            selectedImageByteArray = uri?.let { convertUriToByteArray(activity, it) }
        })
    val state = rememberScrollState()
    LaunchedEffect(Unit) { state.animateScrollTo(0) }
    Scaffold (
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(AdminPanel.Home.route) }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Go back"
                        )
                    }
                },
                title = {
                    Text(text = "Dodaj danie")
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
                )
            )
        },
        content = {innerPadding ->
            var dbHandler: DBHandler = DBHandler(context)
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(state),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .height(270.dp)
                        .padding(15.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .size(270.dp)
                            .clip(RoundedCornerShape(16.dp)),
                        model = selectedImageUri,
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                }
                Surface(
                    modifier = Modifier
                        .height(60.dp)
                        .width(270.dp)
                ) {
                    Button(
                        onClick = { photoPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)) }) {
                        Text("Wybierz zdjęcie")
                    }
                }
                Column(
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp, vertical = 10.dp)
                ) {
                    Text(
                        text = "Nazwa dania",
                        fontSize = MaterialTheme.typography.titleLarge.fontSize
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .width(270.dp),
                ) {
                    OutlinedTextField(
                        value = foodName,
                        onValueChange = { newText ->
                            foodName = newText
                        },
                        label = {
                            Text(text = "Nazwa dania")
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        trailingIcon = {
                            IconButton(onClick = { foodName == "" }) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = "Clear button"
                                )
                            }
                        })
                }
                Column(
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp, vertical = 10.dp)
                ) {
                    Text(
                        text = "Mały rozmiar",
                        fontSize = MaterialTheme.typography.titleLarge.fontSize
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .width(270.dp),
                ) {
                    OutlinedTextField(
                    value = smallPrice,
                    onValueChange = { newText ->
                        smallPrice = newText
                    },
                    label = {
                        Text(text = "Cena")
                    },
                    singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next,
                    ),

                    trailingIcon = {
                        IconButton(onClick = { smallPrice == "" }) {
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
                    OutlinedTextField(
                        value = smallKcal,
                        onValueChange = { newText ->
                            smallKcal = newText
                        },
                        label = {
                            Text(text = "Kaloryczność")
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next,
                        ),
                        trailingIcon = {
                            IconButton(onClick = { smallKcal == "" }) {
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
                    OutlinedTextField(
                        value = smallPortion,
                        onValueChange = { newText ->
                            smallPortion = newText
                        },
                        label = {
                            Text(text = "Porcje (osoby)")
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next,
                        ),
                        trailingIcon = {
                            IconButton(onClick = { smallPortion == "" }) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = "Clear button"
                                )
                            }
                        })
                }
                Surface(
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp, vertical = 10.dp)
                ) {
                    Text(
                        text = "Średni rozmiar",
                        fontSize = MaterialTheme.typography.titleLarge.fontSize
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .width(270.dp),
                ) {
                    OutlinedTextField(
                        value = mediumPrice,
                        onValueChange = { newText ->
                            mediumPrice = newText
                        },
                        label = {
                            Text(text = "Cena")
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next,
                        ),
                        trailingIcon = {
                            IconButton(onClick = { mediumPrice == "" }) {
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
                    OutlinedTextField(
                        value = mediumKcal,
                        onValueChange = { newText ->
                            mediumKcal = newText
                        },
                        label = {
                            Text(text = "Kaloryczność")
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next,
                        ),
                        trailingIcon = {
                            IconButton(onClick = { mediumKcal == "" }) {
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
                    OutlinedTextField(
                        value = mediumPortion,
                        onValueChange = { newText ->
                            mediumPortion = newText
                        },
                        label = {
                            Text(text = "Porcje (osoby)")
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next,
                        ),
                        trailingIcon = {
                            IconButton(onClick = { mediumPortion == "" }) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = "Clear button"
                                )
                            }
                        })
                }
                Surface(
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp, vertical = 10.dp)
                ) {
                    Text(
                        text = "Duży rozmiar",
                        fontSize = MaterialTheme.typography.titleLarge.fontSize
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .width(270.dp),
                ) {
                    OutlinedTextField(
                        value = bigPrice,
                        onValueChange = { newText ->
                            bigPrice = newText
                        },
                        label = {
                            Text(text = "Cena")
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next,
                        ),
                        trailingIcon = {
                            IconButton(onClick = { bigPrice == "" }) {
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
                    OutlinedTextField(
                        value = bigKcal,
                        onValueChange = { newText ->
                            bigKcal = newText
                        },
                        label = {
                            Text(text = "Kaloryczność")
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next,
                        ),
                        trailingIcon = {
                            IconButton(onClick = { bigKcal == "" }) {
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
                    OutlinedTextField(
                        value = bigPortion,
                        onValueChange = { newText ->
                            bigPortion = newText
                        },
                        label = {
                            Text(text = "Porcje (osoby)")
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done,
                        ),
                        trailingIcon = {
                            IconButton(onClick = { bigPortion == "" }) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = "Clear button"
                                )
                            }
                        })
                }
                Surface(
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp, vertical = 10.dp)
                ) {
                    Text(
                        text = "Dodatki",
                        fontSize = MaterialTheme.typography.titleLarge.fontSize
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 5.dp)
                ) {
                    Row(
                        verticalAlignment = CenterVertically
                    ) {
                        Checkbox(
                            checked = isKetchup,
                            onCheckedChange = {
                                isKetchup = it
                            })
                        Text(text = "Ketchup")
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 5.dp)
                ) {
                    Row(
                        verticalAlignment = CenterVertically
                    ) {
                        Checkbox(
                            checked = isGarlic,
                            onCheckedChange = {
                                isGarlic = it
                            })
                        Text(text = "Sos czosnkowy")
                    }
                }
                Surface(
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp, vertical = 10.dp)
                ) {
                    Text(
                        text = "Kategoria",
                        fontSize = MaterialTheme.typography.titleLarge.fontSize
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .width(270.dp),
                ) {
                    ExposedDropdownMenuBox(
                        expanded = isExpanded,
                        onExpandedChange = {isExpanded = it}
                    ) {
                        OutlinedTextField(
                            value = foodCategory,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                            },
                            modifier = Modifier.menuAnchor()
                        )
                        ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }
                        ) {
                            DropdownMenuItem(
                                text = {
                                    Text(text = "Burgery") },
                                onClick = {
                                    foodCategory = "Burgery"
                                    isExpanded = false
                                })
                            DropdownMenuItem(
                                text = {
                                    Text(text = "Desery") },
                                onClick = {
                                    foodCategory = "Desery"
                                    isExpanded = false
                                })
                            DropdownMenuItem(
                                text = {
                                    Text(text = "Dodatki") },
                                onClick = {
                                    foodCategory = "Dodatki"
                                    isExpanded = false
                                })
                            DropdownMenuItem(
                                text = {
                                    Text(text = "Kurczaki") },
                                onClick = {
                                    foodCategory = "Kurczaki"
                                    isExpanded = false
                                })
                            DropdownMenuItem(
                                text = {
                                    Text(text = "Napoje") },
                                onClick = {
                                    foodCategory = "Napoje"
                                    isExpanded = false
                                })
                            DropdownMenuItem(
                                text = {
                                    Text(text = "Pizza") },
                                onClick = {
                                    foodCategory = "Pizza"
                                    isExpanded = false
                                })
                            DropdownMenuItem(
                                text = {
                                    Text(text = "Sałatki") },
                                onClick = {
                                    foodCategory = "Sałatki"
                                    isExpanded = false
                                })
                            DropdownMenuItem(
                                text = {
                                    Text(text = "Wrapy") },
                                onClick = {
                                    foodCategory = "Wrapy"
                                    isExpanded = false
                                })
                        }
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
                        onClick = {
                            try {
                                if(selectedImageByteArray == null || foodName.isEmpty() || smallPrice.isEmpty() || smallKcal.isEmpty() || smallPortion.isEmpty() || mediumPrice.isEmpty() || mediumKcal.isEmpty() || mediumPortion.isEmpty() || bigPrice.isEmpty() || bigKcal.isEmpty() || bigPortion.isEmpty() || foodCategory.isEmpty()) {
                                    Toast.makeText(context, "Uzupełnij brakujące dane!", Toast.LENGTH_SHORT).show()
                                }
                                else {
                                    dbHandler.addNewFood(
                                        image = selectedImageByteArray,
                                        foodName = foodName,
                                        smallPrice = smallPrice.toInt(),
                                        smallKcal = smallKcal.toInt(),
                                        smallPortion = smallPortion.toInt(),
                                        mediumPrice = mediumPrice.toInt(),
                                        mediumKcal = mediumKcal.toInt(),
                                        mediumPortion = mediumPortion.toInt(),
                                        bigPrice = bigPrice.toInt(),
                                        bigKcal = bigKcal.toInt(),
                                        bigPortion = bigPortion.toInt(),
                                        isKetchup = booleanToInt(isKetchup),
                                        isGarlicSauce = booleanToInt(isGarlic),
                                        foodCategory = foodCategory
                                    )
                                    Toast.makeText(context, "Dodano danie pomyślnie!", Toast.LENGTH_SHORT).show()
                                }
                            }
                            catch (e: Exception) {
                                Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show()
                            }
                             }) {
                        Text("Dodaj danie")
                    }
                }
                Surface(
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth()
                ) {}
            }
        }
    )

}

fun booleanToInt(b: Boolean): Int {
    return if (b) 1 else 0
}

private fun convertUriToByteArray(context: Context, uri: Uri): ByteArray? {
    return try {
        context.contentResolver.openInputStream(uri)?.use { inputStream ->
            inputStream.buffered().readBytes()
        }
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}

@Composable
@Preview
fun AddScreenPreview() {
    //AddScreen(LocalContext.current);
}
