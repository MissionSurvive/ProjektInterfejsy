package com.example.foodapp.ClientScreens

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.shape
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodapp.DBHandler
import com.example.foodapp.Foods
import com.example.foodapp.booleanToInt
import java.lang.Exception

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(context: Context, bottomBarState: MutableState<Boolean>) {
    val menuNavController = rememberNavController()
    NavHost(
        navController = menuNavController,
        startDestination = "Menu"
    ) {
        composable("Menu") {
            val sheetState = rememberModalBottomSheetState()
            bottomBarState.value = true
            // Smoothly scroll 100px on first composition
            val state = rememberScrollState()
            LaunchedEffect(Unit) { state.animateScrollTo(0) }
            Scaffold (
                topBar = {
                    Column {
                        CenterAlignedTopAppBar(
                            title = {
                                Text(text = "FoodDroid")
                            },
                            colors = TopAppBarDefaults.smallTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
                            ))
                    }
                },
                content = { innerPadding ->
                    var dbHandler: DBHandler = DBHandler(context)
                    val foodsBurgers = dbHandler.getAllFoods("Burgery")
                    val foodsDesserts = dbHandler.getAllFoods("Desery")
                    val foodsSides = dbHandler.getAllFoods("Dodatki")
                    val foodsChicken = dbHandler.getAllFoods("Kurczaki")
                    val foodsDrinks = dbHandler.getAllFoods("Napoje")
                    val foodsPizza = dbHandler.getAllFoods("Pizza")
                    val foodsSalads = dbHandler.getAllFoods("Sałatki")
                    val foodsWraps = dbHandler.getAllFoods("Wrapy")
                    var query by remember { mutableStateOf("") }
                    var isSearchClicked by remember { mutableStateOf(false) }
                    var burgerSelected by remember { mutableStateOf(true) }
                    var dessertSelected by remember { mutableStateOf(true) }
                    var sideSelected by remember { mutableStateOf(true) }
                    var chickenSelected by remember { mutableStateOf(true) }
                    var drinksSelected by remember { mutableStateOf(true) }
                    var pizzaSelected by remember { mutableStateOf(true) }
                    var saladsSelected by remember { mutableStateOf(true) }
                    var wrapsSelected by remember { mutableStateOf(true) }
                    val filterChips = listOf("Burgery", "Desery", "Dodatki", "Kurczaki", "Napoje", "Pizza", "Sałatki", "Wrapy")
                    var selectedIndices by remember { mutableStateOf(mutableSetOf(0,1,2,3,4,5,6,7)) }
                    val sheetState = rememberModalBottomSheetState()
                    var isSheetOpen by remember { mutableStateOf(false) }
                    var minKcal by remember { mutableStateOf("") }
                    var maxKcal by remember { mutableStateOf("") }
                    var minPortion by remember { mutableStateOf("") }
                    var maxPortion by remember { mutableStateOf("") }
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .verticalScroll(state),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Surface (
                            modifier = Modifier.height(10.dp)
                        )
                        {}
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp))

                        {
                            IconButton(
                                onClick = {
                                    isSheetOpen = true
                                },
                                modifier = Modifier
                                    .size(50.dp) 
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "Send icon"
                                )
                            }
                            TextField(
                                value = query,
                                onValueChange = { newText ->
                                    query = newText
                                },
                                modifier = Modifier
                                    .width(295.dp)
                                    .padding()
                                    .height(50.dp),
                                shape = RoundedCornerShape(24.dp),
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Search,
                                        contentDescription = "Search icon"
                                    )
                                },
                                colors = TextFieldDefaults.textFieldColors(
                                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp),
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent
                                ),
                                singleLine = true,
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Done
                                ),
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        isSearchClicked = true
                                        defaultKeyboardAction(ImeAction.Done)
                                    }),
                                placeholder = {
                                    Text(text = "Wyszukaj")
                                }
                            )
                        }
                        FilterChipGroup(
                            filterChips = filterChips,
                            selectedIndices = selectedIndices,
                            onFilterChipClicked = { index ->
                                    if (index in selectedIndices) {
                                        selectedIndices =
                                            (selectedIndices - index) as MutableSet<Int>
                                        when (index) {
                                            0 -> burgerSelected = false
                                            1 -> dessertSelected = false
                                            2 -> sideSelected = false
                                            3 -> chickenSelected = false
                                            4 -> drinksSelected = false
                                            5 -> pizzaSelected = false
                                            6 -> saladsSelected = false
                                            7 -> wrapsSelected = false
                                        }
                                    } else {
                                        selectedIndices =
                                            (selectedIndices + index) as MutableSet<Int>
                                        when (index) {
                                            0 -> burgerSelected = true
                                            1 -> dessertSelected = true
                                            2 -> sideSelected = true
                                            3 -> chickenSelected = true
                                            4 -> drinksSelected = true
                                            5 -> pizzaSelected = true
                                            6 -> saladsSelected = true
                                            7 -> wrapsSelected = true
                                        }
                                    }
                            }
                        )
                            if(burgerSelected) {
                                Column(
                                    modifier = Modifier
                                        .height(50.dp)
                                        .fillMaxWidth()
                                        .padding(horizontal = 15.dp, vertical = 10.dp)
                                ) {
                                    Text(
                                        text = "Burgery",
                                        fontSize = MaterialTheme.typography.titleLarge.fontSize
                                    )
                                }
                                if (isSearchClicked) {
                                    val filteredFoods = foodsBurgers.filter { food ->
                                        food.name.contains(query, ignoreCase = true)
                                    }

                                    FoodCardRow(
                                        foods = filteredFoods,
                                        query = query,
                                        onClick = { food ->
                                            menuNavController.navigate("DetailedFoodOrder/${food.id}")
                                        }
                                    )
                                } else {
                                    FoodCardRow(
                                        foods = foodsBurgers,
                                        query = query,
                                        onClick = { food ->
                                            menuNavController.navigate("DetailedFoodOrder/${food.id}")
                                        }
                                    )
                                }
                            }
                            if(dessertSelected) {
                                Column(
                                    modifier = Modifier
                                        .height(50.dp)
                                        .fillMaxWidth()
                                        .padding(horizontal = 15.dp, vertical = 10.dp)
                                ) {
                                    Text(
                                        text = "Desery",
                                        fontSize = MaterialTheme.typography.titleLarge.fontSize
                                    )
                                }
                                if (isSearchClicked) {
                                    val filteredFoods = foodsDesserts.filter { food ->
                                        food.name.contains(query, ignoreCase = true)
                                    }
                                    FoodCardRow(
                                        foods = filteredFoods,
                                        query = query,
                                        onClick = { food ->
                                            menuNavController.navigate("DetailedFoodOrder/${food.id}")
                                        }
                                    )
                                } else {
                                    FoodCardRow(
                                        foods = foodsDesserts,
                                        query = query,
                                        onClick = { food ->
                                            menuNavController.navigate("DetailedFoodOrder/${food.id}")
                                        }
                                    )
                                }
                            }
                            if(sideSelected) {
                                Column(
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
                                if (isSearchClicked) {
                                    val filteredFoods = foodsSides.filter { food ->
                                        food.name.contains(query, ignoreCase = true)
                                    }

                                    FoodCardRow(
                                        foods = filteredFoods,
                                        query = query,
                                        onClick = { food ->
                                            menuNavController.navigate("DetailedFoodOrder/${food.id}")
                                        }
                                    )
                                } else {
                                    FoodCardRow(
                                        foods = foodsSides,
                                        query = query,
                                        onClick = { food ->
                                            menuNavController.navigate("DetailedFoodOrder/${food.id}")
                                        }
                                    )
                                }
                            }
                            if(chickenSelected) {
                                Column(
                                    modifier = Modifier
                                        .height(50.dp)
                                        .fillMaxWidth()
                                        .padding(horizontal = 15.dp, vertical = 10.dp)
                                ) {
                                    Text(
                                        text = "Kurczaki",
                                        fontSize = MaterialTheme.typography.titleLarge.fontSize
                                    )
                                }
                                if (isSearchClicked) {
                                    val filteredFoods = foodsChicken.filter { food ->
                                        food.name.contains(query, ignoreCase = true)
                                    }

                                    FoodCardRow(
                                        foods = filteredFoods,
                                        query = query,
                                        onClick = { food ->
                                            menuNavController.navigate("DetailedFoodOrder/${food.id}")
                                        }
                                    )
                                } else {
                                    FoodCardRow(
                                        foods = foodsChicken,
                                        query = query,
                                        onClick = { food ->
                                            menuNavController.navigate("DetailedFoodOrder/${food.id}")
                                        }
                                    )
                                }
                            }
                            if(drinksSelected) {
                                Column(
                                    modifier = Modifier
                                        .height(50.dp)
                                        .fillMaxWidth()
                                        .padding(horizontal = 15.dp, vertical = 10.dp)
                                ) {
                                    Text(
                                        text = "Napoje",
                                        fontSize = MaterialTheme.typography.titleLarge.fontSize
                                    )
                                }
                                if (isSearchClicked) {
                                    val filteredFoods = foodsDrinks.filter { food ->
                                        food.name.contains(query, ignoreCase = true)
                                    }

                                    FoodCardRow(
                                        foods = filteredFoods,
                                        query = query,
                                        onClick = { food ->
                                            menuNavController.navigate("DetailedFoodOrder/${food.id}")
                                        }
                                    )
                                } else {
                                    FoodCardRow(
                                        foods = foodsDrinks,
                                        query = query,
                                        onClick = { food ->
                                            menuNavController.navigate("DetailedFoodOrder/${food.id}")
                                        }
                                    )
                                }
                            }
                            if(pizzaSelected) {
                                Column(
                                    modifier = Modifier
                                        .height(50.dp)
                                        .fillMaxWidth()
                                        .padding(horizontal = 15.dp, vertical = 10.dp)
                                ) {
                                    Text(
                                        text = "Pizza",
                                        fontSize = MaterialTheme.typography.titleLarge.fontSize
                                    )
                                }
                                if (isSearchClicked) {
                                    val filteredFoods = foodsPizza.filter { food ->
                                        food.name.contains(query, ignoreCase = true)
                                    }

                                    FoodCardRow(
                                        foods = filteredFoods,
                                        query = query,
                                        onClick = { food ->
                                            menuNavController.navigate("DetailedFoodOrder/${food.id}")
                                        }
                                    )
                                } else {
                                    FoodCardRow(
                                        foods = foodsPizza,
                                        query = query,
                                        onClick = { food ->
                                            menuNavController.navigate("DetailedFoodOrder/${food.id}")
                                        }
                                    )
                                }
                            }
                            if(saladsSelected) {
                                Column(
                                    modifier = Modifier
                                        .height(50.dp)
                                        .fillMaxWidth()
                                        .padding(horizontal = 15.dp, vertical = 10.dp)
                                ) {
                                    Text(
                                        text = "Sałatki",
                                        fontSize = MaterialTheme.typography.titleLarge.fontSize
                                    )
                                }
                                if (isSearchClicked) {
                                    val filteredFoods = foodsSalads.filter { food ->
                                        food.name.contains(query, ignoreCase = true)
                                    }

                                    FoodCardRow(
                                        foods = filteredFoods,
                                        query = query,
                                        onClick = { food ->
                                            menuNavController.navigate("DetailedFoodOrder/${food.id}")
                                        }
                                    )
                                } else {
                                    FoodCardRow(
                                        foods = foodsSalads,
                                        query = query,
                                        onClick = { food ->
                                            menuNavController.navigate("DetailedFoodOrder/${food.id}")
                                        }
                                    )
                                }
                            }
                            if(wrapsSelected) {
                                Column(
                                    modifier = Modifier
                                        .height(50.dp)
                                        .fillMaxWidth()
                                        .padding(horizontal = 15.dp, vertical = 10.dp)
                                ) {
                                    Text(
                                        text = "Wrapy",
                                        fontSize = MaterialTheme.typography.titleLarge.fontSize
                                    )
                                }
                                if (isSearchClicked) {
                                    val filteredFoods = foodsWraps.filter { food ->
                                        food.name.contains(query, ignoreCase = true)
                                    }

                                    FoodCardRow(
                                        foods = filteredFoods,
                                        query = query,
                                        onClick = { food ->
                                            menuNavController.navigate("DetailedFoodOrder/${food.id}")
                                        }
                                    )
                                } else {
                                    FoodCardRow(
                                        foods = foodsWraps,
                                        query = query,
                                        onClick = { food ->
                                            menuNavController.navigate("DetailedFoodOrder/${food.id}")
                                        }
                                    )
                                }
                            }
                            Surface(
                                modifier = Modifier
                                    .height(100.dp)
                                    .fillMaxWidth()
                            ) {}
                        if(isSheetOpen) {
                            ModalBottomSheet(
                               sheetState = sheetState,
                                onDismissRequest = {
                                   isSheetOpen = false
                                },
                          ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Column(
                                        modifier = Modifier
                                            .height(50.dp)
                                            .fillMaxWidth()
                                            .padding(horizontal = 15.dp, vertical = 10.dp)
                                    ) {
                                        Text(
                                            text = "Kaloryczność (kcal)",
                                            fontSize = MaterialTheme.typography.titleLarge.fontSize
                                        )
                                    }
                                    Column(
                                        modifier = Modifier
                                            .width(270.dp)
                                            .padding(10.dp),
                                    ) {
                                        OutlinedTextField(
                                            value = minKcal,
                                            onValueChange = { newText ->
                                                minKcal = newText
                                            },
                                            label = {
                                                Text(text = "Od")
                                            },
                                            singleLine = true,
                                            keyboardOptions = KeyboardOptions(
                                                keyboardType = KeyboardType.Number,
                                                imeAction = ImeAction.Next,
                                            ),

                                            trailingIcon = {
                                                IconButton(onClick = { minKcal = "" }) {
                                                    Icon(
                                                        imageVector = Icons.Default.Clear,
                                                        contentDescription = "Clear button"
                                                    )
                                                }
                                            })
                                    }
                                    Column(
                                        modifier = Modifier
                                            .width(270.dp)
                                            .padding(10.dp),
                                    ) {
                                        OutlinedTextField(
                                            value = maxKcal,
                                            onValueChange = { newText ->
                                                maxKcal = newText
                                            },
                                            label = {
                                                Text(text = "Do")
                                            },
                                            singleLine = true,
                                            keyboardOptions = KeyboardOptions(
                                                keyboardType = KeyboardType.Number,
                                                imeAction = ImeAction.Next,
                                            ),
                                            trailingIcon = {
                                                IconButton(onClick = { maxKcal = "" }) {
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
                                            text = "Porcje (osoby)",
                                            fontSize = MaterialTheme.typography.titleLarge.fontSize
                                        )
                                    }
                                    Column(
                                        modifier = Modifier
                                            .width(270.dp)
                                            .padding(10.dp),
                                    ) {
                                        OutlinedTextField(
                                            value = minPortion,
                                            onValueChange = { newText ->
                                                minPortion = newText
                                            },
                                            label = {
                                                Text(text = "Od")
                                            },
                                            singleLine = true,
                                            keyboardOptions = KeyboardOptions(
                                                keyboardType = KeyboardType.Number,
                                                imeAction = ImeAction.Next,
                                            ),

                                            trailingIcon = {
                                                IconButton(onClick = { minPortion = "" }) {
                                                    Icon(
                                                        imageVector = Icons.Default.Clear,
                                                        contentDescription = "Clear button"
                                                    )
                                                }
                                            })
                                    }
                                    Column(
                                        modifier = Modifier
                                            .width(270.dp)
                                            .padding(10.dp),
                                    ) {
                                        OutlinedTextField(
                                            value = maxPortion,
                                            onValueChange = { newText ->
                                                maxPortion = newText
                                            },
                                            label = {
                                                Text(text = "Do")
                                            },
                                            singleLine = true,
                                            keyboardOptions = KeyboardOptions(
                                                keyboardType = KeyboardType.Number,
                                                imeAction = ImeAction.Done,
                                            ),
                                            trailingIcon = {
                                                IconButton(onClick = { maxPortion = "" }) {
                                                    Icon(
                                                        imageVector = Icons.Default.Clear,
                                                        contentDescription = "Clear button"
                                                    )
                                                }
                                            })
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
                                                isSheetOpen = false
                                            }) {
                                            Text("Filtruj")
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
                                                minKcal = ""
                                                minPortion = ""
                                                maxKcal = ""
                                                maxPortion = ""
                                            }) {
                                            Text("Wyczyść filtry")
                                        }
                                    }
                                    Surface(
                                        modifier = Modifier
                                            .height(50.dp)
                                    ) {}
                                }
                            }
                          }
                        }
                })
        }
        composable("DetailedFoodOrder/{foodId}") { backStackEntry ->
            bottomBarState.value = false
            val foodId = backStackEntry.arguments?.getString("foodId")
            if(foodId != null) {
                DetailedFoodOrderScreen(
                    context = LocalContext.current,
                    foodId = foodId,
                    navController = menuNavController,
                    bottomBarState = bottomBarState
                )
            }
        }
    }
}

@Composable
fun FoodCardRow(foods: List<Foods>, query: String, onClick: (Foods) -> Unit) {
    val filteredFoods = if (query.isNotEmpty()) {
        foods.filter { food ->
            food.name.contains(query, ignoreCase = true)
        }
    } else {
        foods
    }

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        userScrollEnabled = true
    ) {
        itemsIndexed(filteredFoods) { index, food ->
            FoodCard(
                image = BitmapFactory.decodeByteArray(food.image, 0, food.image.size),
                name = food.name,
                onClick = { onClick(food) }
            )
        }
    }
}

@Composable
fun FoodCard(
    image: Bitmap,
    name: String,
    onClick: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier = Modifier
            .size(width = 150.dp, height = 150.dp)
            .clickable(onClick = onClick)
    ) {
        Image(
            bitmap = image.asImageBitmap(),
            contentDescription = "image",
            modifier = Modifier
                .size(width = 150.dp, height = 100.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = name,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterChipItem(
    label: String,
    isSelected: Boolean,
    onFilterChipClicked: () -> Unit
) {
    FilterChip(
        selected = isSelected,
        onClick = onFilterChipClicked,
        label = { Text(label) },
        leadingIcon = {
            if (isSelected) {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Selected",
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        }
    )
}
@Composable
fun FilterChipGroup(
    filterChips: List<String>,
    selectedIndices: Set<Int>,
    onFilterChipClicked: (Int) -> Unit
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(filterChips.size) { index ->
            FilterChipItem(
                label = filterChips[index],
                isSelected = index in selectedIndices,
                onFilterChipClicked = { onFilterChipClicked(index) }
            )
        }
    }
}