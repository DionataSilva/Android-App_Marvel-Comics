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
    private val characters: MutableList<Character> = mutableListOf(
        Character(),
        Character(),
        Character(),
        Character()
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
            rvCharactersList.adapter = CharacterListAdapter(characters, this, object : CharacterListAdapter.ViewHolder.Callback {

                override fun onClick(characterCard: Character) {
                    callback?.setNextView() ?: throw Exception("Deu Ruim")
                }

            })
            rvCharactersList.layoutManager = GridLayoutManager(this, 2)
        }
    }

    interface Callback {
        fun setNextView()
    }
}