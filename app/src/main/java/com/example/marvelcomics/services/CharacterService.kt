package com.example.marvelcomics.services

import com.example.marvelcomics.BuildConfig.TS
import com.example.marvelcomics.BuildConfig.API_KEY
import com.example.marvelcomics.BuildConfig.HASH
import com.example.marvelcomics.domain.Character
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {

    @GET("characters?ts=$TS&apikey=$API_KEY&hash=$HASH")
    fun list(@Query("offset") offset: Int) : Call<com.example.marvelcomics.domain.Response>
}