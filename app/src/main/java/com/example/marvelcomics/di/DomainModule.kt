package com.example.marvelcomics.di

import com.example.marvelcomics.domain.usecases.DataRequest
import org.koin.dsl.module.module

val domainModule = module {
    factory { DataRequest(get()) }
}