package com.example.foodapp

fun intToBoolean(value: Int): Boolean {
    return when (value) {
        0 -> false
        1 -> true
        else -> throw IllegalArgumentException("Input must be 0 or 1")
    }
}