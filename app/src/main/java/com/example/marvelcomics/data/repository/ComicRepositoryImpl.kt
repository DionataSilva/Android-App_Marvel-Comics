package com.example.marvelcomics.data.repository

import android.util.Log
import com.example.marvelcomics.data.model.ComicItem
import com.example.marvelcomics.data.model.Comics
import com.example.marvelcomics.data.model.Data
import com.example.marvelcomics.data.service.RetrofitInitializer
import com.example.marvelcomics.domain.repository.ComicRepository

class ComicRepositoryImpl: ComicRepository {

    private val api = RetrofitInitializer().service()
    private lateinit var comicsData: Data<ComicItem>

    override suspend fun saveComicsData(comicsData: Data<ComicItem>) {
       this.comicsData = comicsData
    }

    override suspend fun getComicData() = this.comicsData

    override suspend fun getComicsList() = this.comicsData.results

    override suspend fun requestComicsData(offset: Int, characterId: Int): Data<ComicItem>? {
        try {
            val comicsResponse = api.getCharacterComics(characterId, offset)
            Log.e("apiResponse", "$comicsResponse") // TODO: Remover ao final
            return comicsResponse.data
        } catch (e: Exception) {
            Log.e("Exception", e.message) // TODO: Remover ao final
            throw Exception(e.message)
        }
    }
}