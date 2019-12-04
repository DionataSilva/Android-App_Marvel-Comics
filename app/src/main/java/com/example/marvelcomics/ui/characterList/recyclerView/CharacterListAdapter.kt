package com.example.marvelcomics.ui.characterList.recyclerView

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.marvelcomics.R
import com.example.marvelcomics.data.model.Character

class CharacterListAdapter(private val context: Context, private val callback: CharacterListViewHolder.Callback) :
    RecyclerView.Adapter<CharacterListViewHolder>() {

    private val characters: MutableList<Character> = arrayListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.character_card, parent, false)

        return CharacterListViewHolder(view, callback)
    }

    override fun getItemCount() = characters.size

    override fun onBindViewHolder(holder: CharacterListViewHolder, position: Int) {
        holder.bindView(characters[position])
    }

    fun addAll(characters: List<Character>) {
        this.characters.addAll(characters)
        notifyItemRangeChanged(0, characters.size)
    }
}
