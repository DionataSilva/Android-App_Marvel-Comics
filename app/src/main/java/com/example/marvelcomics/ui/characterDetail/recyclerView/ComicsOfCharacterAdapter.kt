package com.example.marvelcomics.ui.characterDetail.recyclerView

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.marvelcomics.R
import com.example.marvelcomics.data.model.Character
import com.example.marvelcomics.data.model.ComicItem
import kotlinx.android.synthetic.main.comic_card.view.*

class ComicsOfCharacterAdapter(
    private val context: Context,
    private val callback: ComicsOfCharacterViewHolder.Callback
) : RecyclerView.Adapter<ComicsOfCharacterViewHolder>() {

    private val comics: MutableList<ComicItem> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsOfCharacterViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.comic_card, parent, false)

        return ComicsOfCharacterViewHolder(view, callback)
    }

    override fun getItemCount() = comics.size

    override fun onBindViewHolder(holder: ComicsOfCharacterViewHolder, position: Int) {
        holder.bindView(comics[position])
    }

    fun addAll(comics: List<ComicItem>) {
        this.comics.addAll(comics)
        notifyItemRangeChanged(0, comics.size)
    }
}
