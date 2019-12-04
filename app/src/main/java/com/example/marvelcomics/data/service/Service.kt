package com.example.marvelcomics.data.service

import com.example.marvelcomics.BuildConfig.*
import com.example.marvelcomics.data.model.MarvelResponseData
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("characters?ts=$TS&apikey=$API_KEY&hash=$HASH")
    suspend fun getCharactersData(@Query("offset") offset: Int): MarvelResponseData

}