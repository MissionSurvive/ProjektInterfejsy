package com.example.foodapp.ClientScreens

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodapp.DBHandler
import com.example.foodapp.Foods

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(context: Context, bottomBarState: MutableState<Boolean>) {
    val menuNavController = rememberNavController()
    NavHost(
        navController = menuNavController,
        startDestination = "Menu"
    ) {
        composable("Menu") {
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
                        /*SearchBar(
                            modifier = if(active) {
                                Modifier
                                    .animateContentSize(spring(stiffness = Spring.StiffnessHigh))
                            } else {
                                Modifier
                                    .fillMaxWidth()
                                    .padding()
                                    .animateContentSize(spring(stiffness = Spring.StiffnessHigh))
                            },
                            query = text,
                            onQueryChange = { text = it },
                            onSearch = {
                                items.add(text)
                                active = false
                                text = "" },
                            active = active,
                            onActiveChange = { active = it },
                            placeholder = {
                                Text(text="Wyszukaj")
                            },
                            leadingIcon = {
                                Icon(imageVector = Icons.Default.Search, contentDescription = "Search icon")
                            },
                            trailingIcon = {
                                if(active) {
                                    Icon(
                                        modifier = Modifier
                                            .clickable {
                                                if(text.isNotEmpty()) {
                                                    text = ""
                                                }
                                                else {
                                                    active = false
                                                }
                                            },
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "Close icon")
                                }
                            },

                            windowInsets = if (active) {
                                SearchBarDefaults.windowInsets
                            } else {
                                WindowInsets(0.dp)
                            }) {
                            items.forEach {
                                Row(modifier = Modifier.padding(all = 14.dp)) {
                                    Icon(
                                        modifier = Modifier.padding(end = 10.dp),
                                        imageVector = Icons.Outlined.History,
                                        contentDescription = "History icon")
                                    Text(text = it)
                                }
                            }
                        }*/
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
                    var burgerSelected by remember { mutableStateOf(true) }
                    var dessertSelected by remember { mutableStateOf(true) }
                    var sideSelected by remember { mutableStateOf(true) }
                    var chickenSelected by remember { mutableStateOf(true) }
                    var drinksSelected by remember { mutableStateOf(true) }
                    var pizzaSelected by remember { mutableStateOf(true) }
                    var saladsSelected by remember { mutableStateOf(true) }
                    var wrapsSelected by remember { mutableStateOf(true) }
                    val filterChips = listOf("Burgery", "Desery", "Dodatki", "Kurczaki", "Napoje", "Pizza", "Sałatki", "Wrapy")
                    var selectedIndices by remember { mutableStateOf(mutableSetOf<Int>()) }
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .verticalScroll(state),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
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
                                FoodCardRow(foods = foodsBurgers) { food ->
                                    menuNavController.navigate("DetailedFoodOrder/${food.id}")
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
                                FoodCardRow(foods = foodsDesserts) { food ->
                                    menuNavController.navigate("DetailedFoodOrder/${food.id}")
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
                                FoodCardRow(foods = foodsSides) { food ->
                                    menuNavController.navigate("DetailedFoodOrder/${food.id}")
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
                                FoodCardRow(foods = foodsChicken) { food ->
                                    menuNavController.navigate("DetailedFoodOrder/${food.id}")
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
                                FoodCardRow(foods = foodsDrinks) { food ->
                                    menuNavController.navigate("DetailedFoodOrder/${food.id}")
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
                                FoodCardRow(foods = foodsPizza) { food ->
                                    menuNavController.navigate("DetailedFoodOrder/${food.id}")
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
                                FoodCardRow(foods = foodsSalads) { food ->
                                    menuNavController.navigate("DetailedFoodOrder/${food.id}")
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
                                FoodCardRow(foods = foodsWraps) { food ->
                                    menuNavController.navigate("DetailedFoodOrder/${food.id}")
                                }
                            }
                            Surface(
                                modifier = Modifier
                                    .height(100.dp)
                                    .fillMaxWidth()
                            ) {}
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

data class CardInfo(
    val image: String,
    val name: String
)

@Composable
fun FoodCardRow(foods: List<Foods>, onClick: (Foods) -> Unit) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        userScrollEnabled = true
    ) {
        itemsIndexed(foods) { index, food ->
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