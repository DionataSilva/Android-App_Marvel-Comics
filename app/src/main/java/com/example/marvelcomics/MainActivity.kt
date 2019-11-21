package com.example.marvelcomics

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import com.example.marvelcomics.adapters.CharacterListAdapter
import com.example.marvelcomics.domain.Character
import com.example.marvelcomics.domain.ComicItem
import com.example.marvelcomics.domain.Comics
import com.example.marvelcomics.domain.Thumbnail
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val thumbnail: Thumbnail = Thumbnail("http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784", "jpg")
    private val comics: Comics = Comics(0, "", listOf())

    private val characters: MutableList<Character> = mutableListOf(
        Character(1, "Teste", "", thumbnail, "http://gateway.marvel.com/v1/public/characters/1011334", comics)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configureLis(characters)
    }

    private fun configureLis(characters: MutableList<Character>) {
        val recyclerView = rvCharactersList

        recyclerView.adapter = CharacterListAdapter(characters, this)

        recyclerView.layoutManager = GridLayoutManager(this, 2)
    }
}
