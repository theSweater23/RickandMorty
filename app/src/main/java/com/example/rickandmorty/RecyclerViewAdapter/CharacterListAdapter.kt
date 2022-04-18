package com.example.rickandmorty.RecyclerViewAdapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.CharacterDetailActivity
import com.example.rickandmorty.MainActivity
import com.example.rickandmorty.R
import com.example.rickandmorty.RetrofitServices.Character
import com.squareup.picasso.Picasso

class CharacterListAdapter(
    private val characterList: List<Character>,
    private val context: Context
    ): RecyclerView.Adapter<CharacterListAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nameTextView: TextView = itemView.findViewById(R.id.name)
        val genderTextView: TextView = itemView.findViewById(R.id.gender)
        val raceTextView: TextView = itemView.findViewById(R.id.race)
        val imageView: ImageView = itemView.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view_item, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val character = characterList[position]

        holder.nameTextView.text = character.name
        holder.genderTextView.text = character.gender
        holder.raceTextView.text = character.species
        holder.itemView.setOnClickListener {
            val intent = Intent(context, CharacterDetailActivity::class.java).apply {
                putExtra("AVATAR", character.imgUrl)
                putExtra("NAME", character.name)
                putExtra("RACE", character.species)
                putExtra("GENDER", character.gender)
                putExtra("STATUS", character.status)
                putExtra("LOCATION", character.location)
                putExtra("EPISODES", character.episodes)
            }
            context.startActivity(intent)
        }

        Picasso.with(context)
            .load(character.imgUrl)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int = characterList.count()
}