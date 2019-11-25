package com.example.marvelcomics

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.example.marvelcomics.adapters.CharacterListAdapter
import com.example.marvelcomics.fragments.CharacterDetailFragment
import com.example.marvelcomics.fragments.CharactersListFragment
import kotlinx.android.synthetic.main.character_card.*

class MainActivity : AppCompatActivity(), CharactersListFragment.Callback {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.flContainer, CharactersListFragment())
            .commit()

    }

    override fun setNextView() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.flContainer, CharacterDetailFragment())
            .addToBackStack(null)
            .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) onBackPressed()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }
}
