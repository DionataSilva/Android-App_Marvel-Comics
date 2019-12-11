package com.example.marvelcomics.ui.characterDetail

import com.example.marvelcomics.data.model.Character
import com.example.marvelcomics.data.model.ComicItem
import com.example.marvelcomics.data.model.Data
import com.example.marvelcomics.domain.usecases.*
import com.example.marvelcomics.ui.abstracts.BasePresenter

class CharacterDetailPresenter(
    private val view: View,
    private val comicsDataRequest: ComicsDataRequest,
    private val getCharacter: GetCharacter,
    private val getComicData: GetComicData,
    private val getComicsList: GetComicsList,
    private val saveComicData: SaveComicData
) : BasePresenter() {

    private var comicsData: Data<ComicItem>? = null
    private var comicsList = mutableListOf<ComicItem>()
    private var character: Character? = null
    private var characterId: Int = 0

    override fun postStart() {
        super.postStart()
        loadCharacterDetail()
        loadComicsList()
    }

    private fun loadCharacterDetail() {
        launchUI {
            character = getCharacter()
            characterId = character!!.id
            setUpViews()
        }
    }

    private fun loadComicsList() {
        var offsetComic = 0

        //var totalComics = 0

        view.callLoader()

        launchUI {
            try {
                comicsData = withDefault { comicsDataRequest.invoke(offsetComic, characterId) }
                comicsData?.let {
                    saveComicData(it)
                    //comicsList.addAll(it.results)
                    //totalComics = it.total
                    offsetComic = it.offset + it.limit
                    comicsList = it.results.toMutableList()
                }
                view.loadComicsList(comicsList)
            } catch (e: Exception) {
                view.showMessageError(e.message)
                comicsList.clear()
                offsetComic = 0
            }

            view.dismissLoader()
        }
    }

    private fun setUpViews() {
        val imageUri = "${character?.thumbnail?.path}.${character?.thumbnail?.extension}"
        view.setUpImage(imageUri)
        character?.description?.let { view.setUpDescription(it) }
    }

    interface View {
        fun callLoader()
        fun setUpImage(imageUri: String)
        fun dismissLoader()
        fun showMessageError(errorMessage: String?)
        fun loadComicsList(list: MutableList<ComicItem>)
        fun setUpDescription(description: String)
    }
}