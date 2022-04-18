package com.example.rickandmorty.RetrofitServices

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET

interface CharacterService {

    @GET("character")
    fun getCharacters(): Call<JsonObject>
}