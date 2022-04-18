package com.example.rickandmorty

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.RecyclerViewAdapter.CharacterListAdapter
import com.example.rickandmorty.RetrofitServices.Character
import com.example.rickandmorty.RetrofitServices.CharacterService
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    var list: MutableList<Character> = mutableListOf()
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(CharacterService::class.java)
        service.getCharacters().enqueue(object: Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                response.body()?.getAsJsonArray("results")?.forEach { character ->
                    list.add(
                        Character(
                            character.asJsonObject.get("id").asInt,
                            character.asJsonObject.get("name").asString,
                            character.asJsonObject.get("gender").asString,
                            character.asJsonObject.get("image").asString,
                            character.asJsonObject.get("species").asString,
                            character.asJsonObject.get("status").asString,
                            character.asJsonObject.get("location").asJsonObject.get("name").asString,
                            character.asJsonObject.get("episode").asJsonArray.size())
                    )
                }
                recyclerView.adapter = CharacterListAdapter(list, this@MainActivity)
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Error occurred", Toast.LENGTH_LONG).show()
            }
        })
    }
}