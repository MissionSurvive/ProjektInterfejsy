package com.example.foodapp.ClientScreens

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.foodapp.Panels.ClientPanel
import com.example.foodapp.Database.DBHandler

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(context: Context, navController: NavController, bottomBarState: MutableState<Boolean>) {
    bottomBarState.value = true
    val state = rememberScrollState()
    LaunchedEffect(Unit) { state.animateScrollTo(0) }
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
            var cartContents = dbHandler.getCartContents()
            var total = dbHandler.getCartTotalPrice()
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(state),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if(total > 0) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        cartContents.forEach { content ->
                            CartFoodCard(
                                image = BitmapFactory.decodeByteArray(content.image, 0, content.image.size),
                                name = content.name,
                                quantity = content.quantity,
                                size = content.size,
                                price = content.price
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .height(50.dp)
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp, vertical = 10.dp)
                    ) {
                        Text(
                            text = "Do zapłaty: " + total.toString() + " zł",
                            fontSize = MaterialTheme.typography.titleLarge.fontSize
                        )
                    }
                        Surface(
                            modifier = Modifier
                                .height(60.dp)
                                .width(270.dp)
                        ) {
                            Button(
                                onClick = { dbHandler.clearCart()
                                    Toast.makeText(context, "Wyczyszczono koszyk!", Toast.LENGTH_SHORT).show()
                                    navController.navigate(ClientPanel.Cart.route)
                                }) {
                                Text("Wyczyść koszyk")
                            } }
                        Surface(
                            modifier = Modifier
                                .height(100.dp)
                                .fillMaxWidth()
                        ) {}
                    }
                else {
                    Column(
                        modifier = Modifier
                            .height(50.dp)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Koszyk jest pusty!",
                            fontSize = MaterialTheme.typography.titleLarge.fontSize
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun CartFoodCard(
    image: Bitmap,
    name: String,
    quantity: Int,
    size: String,
    price: Int
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
                        text = size + ", " + quantity.toString() + "x, " + price.toString() + " zł",
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