package com.example.marvelcomics.ui.characterList

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.marvelcomics.ui.characterList.recyclerView.CharacterListAdapter
import com.example.marvelcomics.data.model.Character
import com.example.marvelcomics.data.model.Response
import com.example.marvelcomics.data.service.CallbackResponse
import com.example.marvelcomics.data.service.character.CharacterWebClient
import com.example.marvelcomics.utils.toast
import kotlinx.android.synthetic.main.fragment_characters_list.*

class CharactersListFragment : Fragment() {
    private var callback: Callback? = null
    private lateinit var gridLayoutManager: GridLayoutManager
    private var charactersPage: Int = 1
    private var totalCharacters: Int = 0
    private var offset: Int = 0

    lateinit var adapter: CharacterListAdapter

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
            com.example.marvelcomics.R.layout.fragment_characters_list,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requestCharacter()
        configureLis()
    }

    private fun requestCharacter() {
        CharacterWebClient().list(
            offset,
            object : CallbackResponse<Response> {
                override fun sucess(response: Response) {
                    totalCharacters = response.data.total
                    adapter.addAll(response.data.results)
                    offset = adapter.itemCount
                }

                override fun fail(error: String) {
                    context?.run {
                        toast("Erro de api")
                    }
                }
            }
        )
    }

    private fun configureLis() {
        context?.run {
            gridLayoutManager = GridLayoutManager(this, 2)
            adapter = CharacterListAdapter(
                this,
                object :
                    CharacterListAdapter.ViewHolder.Callback {

                    override fun onClick(characterCard: Character) {
                        callback?.setNextView() ?: throw Exception("Erro")
                    }

                }
            )

            rvCharactersList.adapter = adapter
            rvCharactersList.addOnScrollListener(onScroll)
            rvCharactersList.layoutManager = gridLayoutManager

        }
    }

    private var onScroll = object : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            val lastVisibleItemPosition =
                gridLayoutManager.findLastVisibleItemPosition() + 1
            val shouldRequest = lastVisibleItemPosition.div(charactersPage) == 20
            val validOffset = (totalCharacters >= adapter.itemCount)

            if (dy > 0 && shouldRequest && validOffset) {
                Log.i("offset", adapter.itemCount.toString())
                requestCharacter()
                offset = adapter.itemCount
                charactersPage++
            } else if (dy < 0) {
                Log.i("Scrolling", "down")
            }
        }
    }

    interface Callback {
        fun setNextView()
    }
}
