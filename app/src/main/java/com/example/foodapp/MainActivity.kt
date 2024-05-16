package com.example.foodapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.compose.FoodAppTheme
import com.example.foodapp.NavigationGraphs.AppNavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodAppTheme { AppNavGraph() }
        }
    }
}