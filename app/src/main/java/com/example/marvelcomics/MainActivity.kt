package com.example.marvelcomics

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.example.marvelcomics.di.appComponent
import com.example.marvelcomics.ui.characterDetail.CharacterDetailFragment
import com.example.marvelcomics.ui.characterList.CharactersListFragment
import org.koin.android.ext.android.startKoin

class MainActivity : AppCompatActivity(), CharactersListFragment.Callback {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)

        startKoin(this, appComponent)

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
