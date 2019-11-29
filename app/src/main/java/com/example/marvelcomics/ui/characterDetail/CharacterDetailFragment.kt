package com.example.marvelcomics.ui.characterDetail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.marvelcomics.R
import com.example.marvelcomics.ui.characterDetail.recyclerView.CharacterDetailAdapter
import com.example.marvelcomics.data.model.Character
import com.example.marvelcomics.data.model.Comics
import com.example.marvelcomics.utils.toast
import kotlinx.android.synthetic.main.fragment_character_detail.*

class CharacterDetailFragment : Fragment() {

    private val character: Character =
        Character()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_character_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureLis(character.comics)
    }

    private fun configureLis(comics: Comics) {
        context?.run {
            rvComicsList.adapter =
                CharacterDetailAdapter(comics, this)
        }
    }
}