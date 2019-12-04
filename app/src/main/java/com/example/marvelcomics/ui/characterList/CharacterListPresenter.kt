package com.example.marvelcomics.ui.characterList

import android.util.Log
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
    private var offsetList: Int = 0

    private var offsetData: Int = 0
    private var totalData: Int = 0

    override fun postStart() {
        super.postStart()
        loadCharacters()
    }

    private fun loadCharacters() {
        view.callLoader() // TODO: Implementar loader

        launchUI {
            try {
                data = withDefault { dataRequest.invoke(offsetList) }
                Log.e("loadCharactersData", "$data") // TODO: Remover ao final
                data?.results?.let { characterList.addAll(it) }
                data?.let {
                    characterList.addAll(it.results)
                    offsetData = it.offset
                    totalData = it.total
                }
                view.loadCharaterList(characterList)
            } catch (e: Exception) {
                view.showMessageError(e.message)
                characterList.clear()
            }

            view.dismissLoader()
        }
    }

    fun getCharacterList(): List<Character> {
        return characterList
    }

    fun reloadList() {
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