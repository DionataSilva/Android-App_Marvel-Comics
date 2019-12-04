package com.example.marvelcomics.di

import com.example.marvelcomics.ui.characterList.CharacterListPresenter
import org.koin.dsl.module.module

val uiModule = module {
    factory { (view: CharacterListPresenter.View) -> CharacterListPresenter(view, get()) }
}