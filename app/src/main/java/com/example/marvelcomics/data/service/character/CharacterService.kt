package com.example.marvelcomics.data.service.character

import com.example.marvelcomics.BuildConfig.TS
import com.example.marvelcomics.BuildConfig.API_KEY
import com.example.marvelcomics.BuildConfig.HASH
import com.example.marvelcomics.data.model.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {

    @GET("characters?ts=$TS&apikey=$API_KEY&hash=$HASH")
    fun list(@Query("offset") offset: Int) : Call<Response>
}