package com.example.foodapp.ClientScreens

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.foodapp.AdminPanel
import com.example.foodapp.BackPressHandler
import com.example.foodapp.ClientPanel
import com.example.foodapp.DBHandler
import com.example.foodapp.booleanToInt
import com.example.foodapp.convertUriToByteArray
import com.example.foodapp.intToBoolean
import java.lang.Exception

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailedFoodOrderScreen(
    context: Context,
    navController: NavController,
    foodId: String,
    bottomBarState: MutableState<Boolean>
) {
    BackPressHandler {
        bottomBarState.value = true // Make the bottom bar visible
        navController.navigate(ClientPanel.Menu.route) {
            popUpTo(navController.graph.findStartDestination().id)
            launchSingleTop = true
        }
    }
    val activity = context as Activity

    var isDatabaseImage by remember { mutableStateOf(true) }
    var isKetchupAvailable by remember { mutableStateOf(true) }
    var isGarlicAvailable by remember { mutableStateOf(true) }
    var garlicStete by remember { mutableStateOf(false) }
    var ketchupState by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("Mały") }

    fun ByteArray.toBitmap(): Bitmap {
        return BitmapFactory.decodeByteArray(this, 0, this.size)
    }

    var selectedImageBitmap by remember {
        mutableStateOf<Bitmap?>(null)
    }

    var selectedImageByteArray by remember {
        mutableStateOf<ByteArray?>(null)
    }

    var photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {uri ->
            selectedImageByteArray = uri?.let { convertUriToByteArray(activity, it) }
            selectedImageBitmap = selectedImageByteArray?.toBitmap()
            isDatabaseImage = false
        })

    val state = rememberScrollState()
    LaunchedEffect(Unit) { state.animateScrollTo(0) }
    Scaffold (
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        bottomBarState.value = true // Make the bottom bar visible
                        navController.navigate(ClientPanel.Menu.route) {
                            popUpTo(navController.graph.findStartDestination().id)
                            launchSingleTop = true
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Go back"
                        )
                    }
                },
                title = {
                    Text(text = "Personalizacja dania")
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
                )
            )
        },
        content = {innerPadding ->
            var dbHandler: DBHandler = DBHandler(context)
            var food = dbHandler.getOneFood(foodId.toInt())
            val queriedFood = food[0]
            var foodName by remember { mutableStateOf(queriedFood.name) }
            var smallPrice by remember { mutableStateOf(queriedFood.sprice.toString()) }
            var smallKcal by remember { mutableStateOf(queriedFood.skcal.toString()) }
            var smallPortion by remember { mutableStateOf(queriedFood.sportion.toString()) }
            var mediumPrice by remember { mutableStateOf(queriedFood.mprice.toString()) }
            var mediumKcal by remember { mutableStateOf(queriedFood.mkcal.toString()) }
            var mediumPortion by remember { mutableStateOf(queriedFood.mportion.toString()) }
            var bigPrice by remember { mutableStateOf(queriedFood.bprice.toString()) }
            var bigKcal by remember { mutableStateOf(queriedFood.bkcal.toString()) }
            var bigPortion by remember { mutableStateOf(queriedFood.bportion.toString()) }
            var isKetchup by remember { mutableStateOf(intToBoolean(queriedFood.isKetchup)) }
            var isGarlic by remember { mutableStateOf(intToBoolean(queriedFood.isGarlic)) }
            var price by remember { mutableStateOf(smallPrice) }
            var kcal by remember { mutableStateOf(smallKcal) }
            var portion by remember { mutableStateOf(smallPortion) }
            var quantity by remember { mutableIntStateOf(1) }

            if(isKetchup == false) {
                isKetchupAvailable = false
            }
            else {
                isKetchupAvailable = true
            }

            if(isGarlic == false) {
                isGarlicAvailable = false
            }
            else {
                isGarlicAvailable = true
            }

            if(selectedOption == "Mały") {
                price = smallPrice
                kcal = smallKcal
                portion = smallPortion
            }
            else if(selectedOption == "Średni") {
                price = mediumPrice
                kcal = mediumKcal
                portion = mediumPortion
            }
            else if(selectedOption == "Duży") {
                price = bigPrice
                kcal = bigKcal
                portion = bigPortion
            }

            if(quantity < 1) {
                quantity = 1
            }

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
                        model = if (isDatabaseImage) queriedFood?.image?.toBitmap() else selectedImageBitmap,
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                }
                Surface(
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp, vertical = 10.dp)
                ) {
                    Text(
                        text = foodName,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize
                    )
                }
                Surface(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                ) {
                    Text(
                        text = price + " zł",
                        fontSize = MaterialTheme.typography.titleLarge.fontSize
                    ) }
                Surface(
                    modifier = Modifier
                        .height(35.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                ) {
                    Text(
                        text = kcal + " kcal",
                        fontSize = MaterialTheme.typography.titleMedium.fontSize
                    ) }
                Surface(
                    modifier = Modifier
                        .height(30.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                ) {
                    Text(
                        text = "Dla osób: " + portion,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize
                    ) }
                Surface(
                    modifier = Modifier
                        .height(30.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                ) {
                    Text(
                        text = "Rozmiar",
                        fontSize = MaterialTheme.typography.titleLarge.fontSize
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 5.dp)
                ) {
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selectedOption == "Mały",
                            onClick = { selectedOption = "Mały" },
                        )
                        Text("Mały")
                    }
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selectedOption == "Średni",
                            onClick = { selectedOption = "Średni" },
                        )
                        Text("Średni")
                    }
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selectedOption == "Duży",
                            onClick = { selectedOption = "Duży" },
                        )
                        Text("Duży")
                    }
                }
                Surface(
                    modifier = Modifier
                        .height(30.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                ) {
                    Text(
                        text = "Dodatki",
                        fontSize = MaterialTheme.typography.titleLarge.fontSize
                    ) }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 5.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            enabled = isKetchupAvailable,
                            checked = ketchupState,
                            onCheckedChange = {
                                ketchupState = it
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
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            enabled = isGarlicAvailable,
                            checked = garlicStete,
                            onCheckedChange = {
                                garlicStete = it
                            })
                        Text(text = "Sos czosnkowy")
                    }
                }
                Surface(
                    modifier = Modifier
                        .height(30.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                ) {
                    Text(
                        text = "Ilość sztuk",
                        fontSize = MaterialTheme.typography.titleLarge.fontSize
                    ) }
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {
                            quantity -= 1
                        },
                                colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.errorContainer, // Set the background color to red
                                    contentColor = Color(255,255,255)
                    )) {
                        Text("-")
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = quantity.toString(),
                        fontSize = MaterialTheme.typography.titleMedium.fontSize
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Button(
                        onClick = {
                            quantity += 1
                        }) {
                        Text("+")
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
                                dbHandler.addToCart(
                                    cartPrice = price.toInt() * quantity,
                                    cartQuantity = quantity,
                                    cartSize = selectedOption,
                                    isKetchup = booleanToInt(ketchupState),
                                    isGarlicSauce = booleanToInt(garlicStete),
                                    foodId = foodId.toInt()
                                    )
                                Toast.makeText(context, "Dodano do koszyka!", Toast.LENGTH_SHORT).show()

                            }
                            catch (e: Exception) {
                                Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show()
                            }
                        }) {
                        Text("Dodaj do koszyka")
                    }
                }
                Surface(
                    modifier = Modifier
                        .height(10.dp)
                        .fillMaxWidth()
                ) {}
            }
        }
    )
}