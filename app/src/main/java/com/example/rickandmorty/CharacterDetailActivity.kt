package com.example.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class CharacterDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)

        val avatar: ImageView = findViewById(R.id.avatar)
        val name: TextView = findViewById(R.id.character_name)
        val race: TextView = findViewById(R.id.character_race)
        val gender: TextView = findViewById(R.id.character_gender)
        val status: TextView = findViewById(R.id.character_status)
        val location: TextView = findViewById(R.id.location)
        val episodes: TextView = findViewById(R.id.episodes)

        val info = intent

        Picasso.with(this)
            .load(info.getStringExtra("AVATAR"))
            .into(avatar)

        name.text = info.getStringExtra("NAME")
        race.text = "Race: " + info.getStringExtra("RACE")
        gender.text = "Gender: " + info.getStringExtra("GENDER")
        status.text = "Status: " + info.getStringExtra("STATUS")
        location.text = "Location: " + info.getStringExtra("LOCATION")
        episodes.text = "Episodes: " + info.getIntExtra("EPISODES", 1).toString()
    }
}