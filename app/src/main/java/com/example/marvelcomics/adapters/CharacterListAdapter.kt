package com.example.marvelcomics.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.marvelcomics.R
import com.example.marvelcomics.domain.Character
import kotlinx.android.synthetic.main.character_card.view.*

class CharacterListAdapter(
    private val characters: MutableList<Character>,
    private val context: Context,
    private val callback: ViewHolder.Callback
) : RecyclerView.Adapter<CharacterListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.character_card, parent, false)
        return ViewHolder(view, callback)
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = characters[position]
        holder.bindView(character)
    }

    class ViewHolder(itemView: View, private val callback: Callback) : RecyclerView.ViewHolder(itemView) {

        fun bindView(characterCard: Character) = with(itemView) {
            val imageURI = "${characterCard.thumbnail.path}.${characterCard.thumbnail.extension}"

            tvCharacterName.text = characterCard.name
            Glide.with(itemView.context).load(imageURI).into(ivCharacterImage)

            itemView.setOnClickListener { callback.onClick(characterCard) }
        }

        interface Callback {
            fun onClick(characterCard: Character)
        }
    }

}
