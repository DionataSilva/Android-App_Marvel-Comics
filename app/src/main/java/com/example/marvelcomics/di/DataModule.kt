package com.example.marvelcomics.di

import com.example.marvelcomics.data.repository.ComicRepositoryImpl
import com.example.marvelcomics.data.repository.CharacterRepositoryImpl
import com.example.marvelcomics.domain.repository.ComicRepository
import com.example.marvelcomics.domain.repository.CharacterRepository
import org.koin.dsl.module.module

val dataModule = module {
    single<CharacterRepository> { CharacterRepositoryImpl() }
    single<ComicRepository> { ComicRepositoryImpl() }
}