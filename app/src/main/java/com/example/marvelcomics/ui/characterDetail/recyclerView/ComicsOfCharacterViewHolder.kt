package com.example.marvelcomics.ui.characterDetail.recyclerView

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.example.marvelcomics.data.model.Character
import com.example.marvelcomics.data.model.ComicItem
import com.example.marvelcomics.ui.characterList.recyclerView.CharacterListViewHolder
import kotlinx.android.synthetic.main.comic_card.view.*

class ComicsOfCharacterViewHolder(
    itemView: View,
    private val callback: Callback
) : RecyclerView.ViewHolder(itemView) {

    fun bindView(comicCard: ComicItem) = with(itemView) {
        val imageURI = "${comicCard.thumbnail.path}.${comicCard.thumbnail.extension}"

        Glide.with(itemView.context).load(imageURI).into(ivComicImage)

        itemView.setOnClickListener { callback.onClick(comicCard) }
    }

    interface Callback {
        fun onClick(comicCard: ComicItem)
    }
}