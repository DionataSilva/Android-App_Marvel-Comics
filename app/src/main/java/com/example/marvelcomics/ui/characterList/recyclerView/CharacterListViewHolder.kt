package com.example.marvelcomics.ui.characterList.recyclerView

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.example.marvelcomics.data.model.Character
import kotlinx.android.synthetic.main.character_card.view.*

class CharacterListViewHolder(itemView: View, private val callback: Callback) : RecyclerView.ViewHolder(itemView) {

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