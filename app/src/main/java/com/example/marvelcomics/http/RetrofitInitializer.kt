package com.example.marvelcomics.http

import com.example.marvelcomics.services.CharacterService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    private fun interceptor():HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

       return interceptor
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://gateway.marvel.com/v1/public/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()


    fun characterService(): CharacterService = retrofit.create(CharacterService::class.java)
}