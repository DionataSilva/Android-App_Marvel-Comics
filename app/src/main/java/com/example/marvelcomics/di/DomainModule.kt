package com.example.marvelcomics.di

import com.example.marvelcomics.domain.usecases.*
import org.koin.dsl.module.module

val domainModule = module {
    factory { CharactersDataRequest(get()) }
    factory { ComicsDataRequest(get()) }
    factory { SaveCharacter(get()) }
    factory { GetCharacter(get()) }
    factory { SaveComicData(get()) }
    factory { GetComicData(get()) }
    factory { GetComicsList(get()) }
}