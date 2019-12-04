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
import com.example.marvelcomics.R.layout.*
import com.example.marvelcomics.ui.characterList.recyclerView.CharacterListViewHolder
import kotlinx.android.synthetic.main.fragment_characters_list.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class CharactersListFragment : Fragment(), CharacterListPresenter.View {

    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var adapter: CharacterListAdapter
    private lateinit var callback: Callback
    private var offset: Int = 0
    private var charactersPage: Int = 1
    private var totalCharacters: Int = 0
    private val presenter: CharacterListPresenter by inject { parametersOf(this) }
    private var onScroll = object : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            val lastVisibleItemPosition =
                gridLayoutManager.findLastVisibleItemPosition() + 1
            val shouldRequest = lastVisibleItemPosition.div(charactersPage) == 20
            val validOffset = (totalCharacters >= adapter.itemCount)

            if (dy > 0 && shouldRequest && validOffset) {
                Log.i("offset", adapter.itemCount.toString()) // TODO: Remover ao final
                reloadCharacterList()
                offset = adapter.itemCount
                charactersPage++
            } else if (dy < 0) {
                Log.i("Scrolling", "down") // TODO: Remover ao final
            }
        }
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        callback = context as Callback
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(fragment_characters_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.start()

        configureList()
    }

    override fun callLoader() {
        // TODO: not implemented
    }

    override fun dismissLoader() {
        // TODO: not implemented
    }

    override fun loadCharaterList(list: List<Character>) {
        Log.e("CharaterList", list.toString()) // TODO: Remover ao final
        adapter.addAll(list)
    }

    override fun showMessageError(errorMessage: String?) {
        Log.e("Error", errorMessage) // TODO: Remover ao final
    }


    private fun configureList() {
        context?.run {
            gridLayoutManager = GridLayoutManager(this, 2)

            adapter = CharacterListAdapter(this, object : CharacterListViewHolder.Callback {
                override fun onClick(characterCard: Character) {
                    callback.setNextView()
                }
            })

            rvCharactersList.adapter = adapter
            rvCharactersList.addOnScrollListener(onScroll)
            rvCharactersList.layoutManager = gridLayoutManager
        }
    }

    private fun getCharacterList() = presenter.getCharacterList()

    private fun reloadCharacterList() = presenter.reloadList()

    interface Callback {
        fun setNextView()
    }
}
