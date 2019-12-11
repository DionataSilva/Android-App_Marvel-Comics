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
import com.example.marvelcomics.ProgressLoader
import com.example.marvelcomics.extentions.toast
import com.example.marvelcomics.ui.characterList.recyclerView.CharacterListAdapter
import com.example.marvelcomics.data.model.Character
import com.example.marvelcomics.R.layout.*
import com.example.marvelcomics.ui.characterList.recyclerView.CharacterListViewHolder
import kotlinx.android.synthetic.main.fragment_characters_list.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import kotlin.properties.Delegates

class CharactersListFragment : Fragment(), CharacterListPresenter.View {

    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var adapter: CharacterListAdapter
    private lateinit var callback: Callback
    private var charactersPage by Delegates.notNull<Int>()
    private val presenter: CharacterListPresenter by inject { parametersOf(this) }
    private var onScroll = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

            val lastVisibleItemPosition = gridLayoutManager.findLastVisibleItemPosition() + 1
            val shouldRequest = lastVisibleItemPosition.div(charactersPage) == 20

            if (dy > 0 && shouldRequest) {
                presenter.loadMorCharacters()
                charactersPage++
            } else if (dy < 0) {
                // TODO: implementar aqui qualquer ação necessária quando a lista for scroolada para cima
                Log.i("dy < 0", "Scrool down")
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
        context?.run {
            ProgressLoader.call(this)
        }
    }

    override fun dismissLoader() = ProgressLoader.dismiss()

    override fun loadCharaterList(list: List<Character>) = adapter.addAll(list)

    override fun showMessageError(errorMessage: String?) {
        context?.run {
            this.toast("$errorMessage")
        }
    }

    override fun loadDetailCharacter() = callback.setNextView()


    private fun configureList() {
        charactersPage = 1

        context?.run {
            gridLayoutManager = GridLayoutManager(this, 2)

            adapter = CharacterListAdapter(this, object : CharacterListViewHolder.Callback {
                override fun onClick(characterCard: Character) {
                    presenter.onClickList(characterCard)
                }
            })

            rvCharactersList.adapter = adapter
            rvCharactersList.addOnScrollListener(onScroll)
            rvCharactersList.layoutManager = gridLayoutManager
        }
    }

    interface Callback {
        fun setNextView()
    }
}
