package com.example.foodapp.AdminScreens

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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodapp.DBHandler
import com.example.foodapp.Foods

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(context: Context, bottomBarState: MutableState<Boolean>) {
    val homeNavController = rememberNavController()
    NavHost(
        navController = homeNavController,
        startDestination = "Home"
    ) {
        composable("Home") {
            bottomBarState.value = true
            // Smoothly scroll 100px on first composition
            val state = rememberScrollState()
            LaunchedEffect(Unit) { state.animateScrollTo(0) }
            Scaffold (
                topBar = {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(text = "Panel Administarcyjny")
                        },
                        colors = TopAppBarDefaults.smallTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
                        )
                    )
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
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .verticalScroll(state),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
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
                            homeNavController.navigate("DetailedFoodProperties/${food.id}")
                        }
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
                            homeNavController.navigate("DetailedFoodProperties/${food.id}")
                        }
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
                            homeNavController.navigate("DetailedFoodProperties/${food.id}")
                        }
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
                            homeNavController.navigate("DetailedFoodProperties/${food.id}")
                        }
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
                            homeNavController.navigate("DetailedFoodProperties/${food.id}")
                        }
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
                            homeNavController.navigate("DetailedFoodProperties/${food.id}")
                        }
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
                            homeNavController.navigate("DetailedFoodProperties/${food.id}")
                        }
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
                            homeNavController.navigate("DetailedFoodProperties/${food.id}")
                        }
                        Surface(
                            modifier = Modifier
                                .height(100.dp)
                                .fillMaxWidth()
                        ) {}
                    }
                })
        }
        composable("DetailedFoodProperties/{foodId}") { backStackEntry ->
            bottomBarState.value = false
            val foodId = backStackEntry.arguments?.getString("foodId")
            if(foodId != null) {
                DetailedFoodPropertiesScreen(
                    context = LocalContext.current,
                    foodId = foodId,
                    navController = homeNavController,
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
            .clickable (onClick = onClick)
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

@Composable
@Preview
fun HomeScreenPreview() {
    //HomeScreen(LocalContext.current);
}
