package com.example.marvelcomics.ui.characterList

import com.example.marvelcomics.data.model.Character
import com.example.marvelcomics.data.model.Data
import com.example.marvelcomics.domain.usecases.CharactersDataRequest
import com.example.marvelcomics.domain.usecases.SaveCharacter
import com.example.marvelcomics.ui.abstracts.BasePresenter

class CharacterListPresenter(
    private val view: View,
    private val charactersDataRequest: CharactersDataRequest,
    private val saveCharacter: SaveCharacter
) : BasePresenter() {

    private var data: Data<Character>? = null
    private var characterList = mutableListOf<Character>()

    private var offsetData: Int = 0
    private var totalData: Int = 0

    override fun postStart() {
        super.postStart()
        clearOnStart()
        loadCharacters()
    }

    private fun clearOnStart() {
        data = null
        characterList.clear()
        offsetData = 0
        totalData = 0
    }

    private fun loadCharacters() {
        view.callLoader()

        launchUI {
            try {
                data = withDefault { charactersDataRequest.invoke(offsetData) }
                data?.let {
                    characterList.addAll(it.results)
                    totalData = it.total
                    offsetData = it.offset + it.limit
                }
                view.loadCharaterList(characterList)
            } catch (e: Exception) {
                view.showMessageError(e.message)
                characterList.clear()
                offsetData = 0
            }

            view.dismissLoader()
        }
    }

    fun loadMorCharacters() {
        characterList.clear()
        loadCharacters()
    }

    fun onClickList(character: Character) {
        launchUI {
            saveCharacter(character)
            view.loadDetailCharacter()
        }
    }

    interface View {
        fun callLoader()
        fun dismissLoader()
        fun showMessageError(errorMessage: String?)
        fun loadCharaterList(list: List<Character>)
        fun loadDetailCharacter()
    }
}