package com.example.marvelcomics.domain.usecases

import com.example.marvelcomics.data.model.ComicItem
import com.example.marvelcomics.data.model.Data
import com.example.marvelcomics.domain.repository.ComicRepository

class ComicsDataRequest(private val repository: ComicRepository) {

    suspend operator fun invoke(offset: Int, characterId: Int): Data<ComicItem>? {
        return repository.requestComicsData(offset, characterId)
    }

}