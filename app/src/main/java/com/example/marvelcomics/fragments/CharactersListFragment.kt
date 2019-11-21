package com.example.marvelcomics.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.marvelcomics.R
import com.example.marvelcomics.adapters.CharacterListAdapter
import com.example.marvelcomics.domain.Character
import com.example.marvelcomics.domain.Comics
import com.example.marvelcomics.domain.Thumbnail
import kotlinx.android.synthetic.main.fragment_characters_list.*
import java.lang.Exception

class CharactersListFragment : Fragment() {
    private val thumbnail: Thumbnail =
        Thumbnail("http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784", "jpg")
    private val comics: Comics = Comics(0, "", listOf())
    private val characters: MutableList<Character> = mutableListOf(
        Character(
            1,
            "Teste",
            "",
            thumbnail,
            "http://gateway.marvel.com/v1/public/characters/1011334",
            comics
        )
    )
    var callback: Callback? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        callback = context as Callback
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_characters_list,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureLis(characters)
    }

    private fun configureLis(characters: MutableList<Character>) {
        context?.run {
            val recyclerView = rvCharactersList

            recyclerView.adapter = CharacterListAdapter(characters, this, object : CharacterListAdapter.ViewHolder.Callback {

                override fun onClick(characterCard: Character) {
                    callback?.setNextView() ?: throw Exception("Deu Ruim")
                }

            })
            recyclerView.layoutManager = GridLayoutManager(this, 2)
        }
    }

    interface Callback {
        fun setNextView()
    }
}