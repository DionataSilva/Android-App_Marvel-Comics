package com.example.marvelcomics.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.marvelcomics.adapters.CharacterDetailAdapter
import com.example.marvelcomics.domain.Character
import com.example.marvelcomics.domain.Comics
import kotlinx.android.synthetic.main.fragment_character_detail.*

class CharacterDetailFragment : Fragment() {

    private val character: Character = Character()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            com.example.marvelcomics.R.layout.fragment_character_detail,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureLis(character.comics)
    }

    private fun configureLis(comics: Comics) {
        context?.run {
            rvComicsList.adapter = CharacterDetailAdapter(comics, this)
        }
    }
}
