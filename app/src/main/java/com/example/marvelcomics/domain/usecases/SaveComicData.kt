package com.example.marvelcomics.domain.usecases

import com.example.marvelcomics.data.model.ComicItem
import com.example.marvelcomics.data.model.Data
import com.example.marvelcomics.domain.repository.ComicRepository

class SaveComicData(private val repository: ComicRepository) {

    suspend operator fun invoke(comicsData: Data<ComicItem>){
        repository.saveComicsData(comicsData)
    }

}