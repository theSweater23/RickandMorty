package com.example.rickandmorty.RetrofitServices

data class Character(
    val id: Int,
    val name: String,
    val gender: String,
    val imgUrl: String,
    val species: String,
    val status: String,
    val location: String,
    val episodes: Int
)