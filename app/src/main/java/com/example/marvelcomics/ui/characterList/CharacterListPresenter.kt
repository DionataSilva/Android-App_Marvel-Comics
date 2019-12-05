package com.example.marvelcomics.ui.characterList

import com.example.marvelcomics.data.model.Character
import com.example.marvelcomics.data.model.Data
import com.example.marvelcomics.domain.usecases.DataRequest
import com.example.marvelcomics.ui.abstracts.BasePresenter

class CharacterListPresenter(
    private val view: View,
    private val dataRequest: DataRequest
) : BasePresenter() {

    private var data: Data? = null
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
        view.callLoader() // TODO: Implementar loader

        launchUI {
            try {
                data = withDefault { dataRequest.invoke(offsetData) }
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

    fun onClickList() {
        launchUI {
            // TODO: Implementar
        }
    }

    // TODO: Implementar Funções para pegar offSet e total
    interface View {
        fun callLoader()
        fun dismissLoader()
        fun showMessageError(errorMessage: String?)
        fun loadCharaterList(list: List<Character>)
    }
}