package com.example.marvelcomics.data.service

import com.example.marvelcomics.BuildConfig.*
import com.example.marvelcomics.data.model.Character
import com.example.marvelcomics.data.model.ComicItem
import com.example.marvelcomics.data.model.MarvelResponseData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {

    @GET("characters?ts=$TS&apikey=$API_KEY&hash=$HASH")
    suspend fun getCharactersData(@Query("offset") offset: Int): MarvelResponseData<Character>

    @GET("characters/{characterId}/comics?ts=$TS&apikey=$API_KEY&hash=$HASH")
    suspend fun getCharacterComics(
        @Path("characterId") characterId: Int,
        @Query("offset") offset: Int
    ): MarvelResponseData<ComicItem>

}