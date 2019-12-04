package com.example.marvelcomics.data.service

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


    fun service(): Service = retrofit.create(Service::class.java)
}