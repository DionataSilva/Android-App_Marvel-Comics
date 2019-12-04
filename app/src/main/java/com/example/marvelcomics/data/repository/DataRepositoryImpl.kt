package com.example.marvelcomics.data.repository

import android.util.Log
import com.example.marvelcomics.data.model.Data
import com.example.marvelcomics.data.service.RetrofitInitializer
import com.example.marvelcomics.domain.repository.DataRepository

class DataRepositoryImpl : DataRepository {

    private val api = RetrofitInitializer().service()

    override suspend fun getData(offset: Int): Data? {
        try {
            val charactersResponse = api.getCharactersData(offset)
            Log.e("apiResponse", "$charactersResponse") // TODO: Remover ao final
            return charactersResponse.data
        } catch (e: Exception) {
            Log.e("Exception", e.message) // TODO: Remover ao final
            throw Exception("FAILURE")
        }
    }
}
