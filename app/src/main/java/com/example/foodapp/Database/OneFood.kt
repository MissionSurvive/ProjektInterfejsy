package com.example.foodapp.Database

data class OneFood(
    val id: Int, val image: ByteArray, val name: String, val sprice: Int, val skcal: Int, val sportion: Int, val mprice: Int, val mkcal: Int, val mportion: Int, val bprice: Int, val bkcal: Int, val bportion: Int, val isKetchup: Int, val isGarlic: Int, val category: String
)