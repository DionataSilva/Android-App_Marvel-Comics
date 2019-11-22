package com.example.marvelcomics.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.marvelcomics.R
import com.example.marvelcomics.domain.ComicItem
import com.example.marvelcomics.domain.Comics
import kotlinx.android.synthetic.main.comic_card.view.*

class CharacterDetailAdapter(
    private val comics: Comics,
    private val context: Context
    ) : RecyclerView.Adapter<CharacterDetailAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.comic_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return comics.items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(comics.items[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(comicCard: ComicItem) = with(itemView) {

            val imageURI = "${comicCard.thumbnail.path}.${comicCard.thumbnail.extension}"

            Glide.with(itemView.context).load(imageURI).into(ivComicImage)
        }
    }
}
