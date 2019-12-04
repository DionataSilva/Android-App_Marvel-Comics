package com.example.marvelcomics.di

import com.example.marvelcomics.data.repository.DataRepositoryImpl
import com.example.marvelcomics.domain.repository.DataRepository
import org.koin.dsl.module.module

val dataModule = module {
    single<DataRepository> { DataRepositoryImpl() }
}