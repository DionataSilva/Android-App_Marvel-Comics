package com.example.marvelcomics.ui.characterDetail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.example.marvelcomics.ProgressLoader
import com.example.marvelcomics.R
import com.example.marvelcomics.data.model.Character
import com.example.marvelcomics.data.model.ComicItem
import com.example.marvelcomics.extentions.toast
import com.example.marvelcomics.ui.characterDetail.recyclerView.ComicsOfCharacterAdapter
import com.example.marvelcomics.ui.characterDetail.recyclerView.ComicsOfCharacterViewHolder
import kotlinx.android.synthetic.main.fragment_character_detail.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class CharacterDetailFragment : Fragment(), CharacterDetailPresenter.View {

    private lateinit var adapter: ComicsOfCharacterAdapter
    private val presenter: CharacterDetailPresenter by inject { parametersOf(this) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_character_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.start()
        configureList()
    }

    override fun callLoader() {
        context?.run {
            ProgressLoader.call(this)
        }
    }

    override fun dismissLoader()= ProgressLoader.dismiss()

    override fun showMessageError(errorMessage: String?) {
        context?.run {
            this.toast("$errorMessage")
        }
        Log.e("errorMessage", errorMessage)
    }

    override fun loadComicsList(list: MutableList<ComicItem>) {
        adapter.addAll(list)
    }

    override fun setUpImage(imageUri: String) {
        context?.run {
            Glide.with(this).load(imageUri).into(ivCharacterImage)
        }
    }

    override fun setUpDescription(description: String) {
        tvCharacterDescription.text = description
    }


    private fun configureList() {
        context?.run {
            adapter = ComicsOfCharacterAdapter(this, object : ComicsOfCharacterViewHolder.Callback {
                override fun onClick(comicCard: ComicItem) {
                    toast("Clicked Comic")
                }
            })

            rvComicsList.adapter = adapter
        }
    }
}