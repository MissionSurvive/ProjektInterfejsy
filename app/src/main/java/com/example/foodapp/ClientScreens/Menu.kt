package com.example.foodapp.ClientScreens

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.foodapp.DBHandler
import com.example.foodapp.Foods

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(context: Context) {

    // Smoothly scroll 100px on first composition
    val state = rememberScrollState()
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    var items = remember { mutableStateListOf("<sample_search>") }
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
                FoodCardRow(foods = foodsBurgers)
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
                FoodCardRow(foods = foodsDesserts)
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
                FoodCardRow(foods = foodsSides)
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
                FoodCardRow(foods = foodsChicken)
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
                FoodCardRow(foods = foodsDrinks)
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
                FoodCardRow(foods = foodsPizza)
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
                FoodCardRow(foods = foodsSalads)
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
                FoodCardRow(foods = foodsWraps)
                Surface(
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth()
                ) {}
            }
        })
}

data class CardInfo(
    val image: String,
    val name: String
)

@Composable
fun FoodCardRow(foods: List<Foods>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        userScrollEnabled = true
    ) {
        itemsIndexed(foods) { index, food ->
            FoodCard(
                image = BitmapFactory.decodeByteArray(food.image, 0, food.image.size),
                name = food.name
            )
        }
    }
}

@Composable
fun FoodCard(
    image: Bitmap,
    name: String
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier = Modifier
            .size(width = 150.dp, height = 150.dp)
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