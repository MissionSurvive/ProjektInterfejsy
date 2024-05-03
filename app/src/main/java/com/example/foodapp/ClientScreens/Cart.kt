package com.example.foodapp.ClientScreens

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.foodapp.ClientPanel
import com.example.foodapp.DBHandler
import com.example.foodapp.Foods

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(context: Context, navController: NavController, bottomBarState: MutableState<Boolean>) {
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
                    Text(text = "Koszyk")
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
                )
            )
        },
        content = {innerPadding ->
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
                modifier = Modifier.padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                FoodCardColumn(foods = foodsSides)
                Column(
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp, vertical = 10.dp)
                ) {
                    Text(
                        text = "Do zapłaty:",
                        fontSize = MaterialTheme.typography.titleLarge.fontSize
                    )
                }
                Column(
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                ) {
                    Text(
                        text = "<total_price>",
                        fontSize = MaterialTheme.typography.titleLarge.fontSize
                    )
                }
                Surface(
                    modifier = Modifier
                        .height(60.dp)
                        .width(270.dp)
                ) {
                    Button(
                        onClick = { /*TODO*/ }) {
                        Text("Wyczyść koszyk")
                    } }
            }
        }
    )
}

@Composable
fun FoodCardColumn(foods: List<Foods>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        //userScrollEnabled = true
    ) {
        itemsIndexed(foods) { index, food ->
            CartFoodCard(
                image = BitmapFactory.decodeByteArray(food.image, 0, food.image.size),
                name = food.name
            )
        }
    }
}

@Composable
fun CartFoodCard(
    image: Bitmap,
    name: String
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier = Modifier
            .size(width = 350.dp, height = 75.dp)
    ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Column (
                    modifier = Modifier
                        .padding(horizontal = 10.dp),
                    horizontalAlignment = Alignment.Start
                ){
                    Text(
                        text = name,
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "<price>",
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize
                    )
                }
                Image(
                    bitmap = image.asImageBitmap(),
                    contentDescription = "image",
                    modifier = Modifier
                        .size(width = 75.dp, height = 75.dp),
                    contentScale = ContentScale.Crop
                )
            }
    }
}