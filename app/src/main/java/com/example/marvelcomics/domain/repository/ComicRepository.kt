package com.example.marvelcomics.domain.repository

import com.example.marvelcomics.data.model.ComicItem
import com.example.marvelcomics.data.model.Data

interface ComicRepository {

    suspend fun requestComicsData(offset: Int, characterId: Int) : Data<ComicItem>?

    suspend fun saveComicsData(comicsData: Data<ComicItem>)

    suspend fun getComicData() : Data<ComicItem>

    suspend fun getComicsList() : List<ComicItem>
}