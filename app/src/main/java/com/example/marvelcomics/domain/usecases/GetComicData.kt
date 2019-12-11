package com.example.marvelcomics.domain.usecases

import com.example.marvelcomics.domain.repository.ComicRepository

class GetComicData(private val repository: ComicRepository) {

    suspend operator fun invoke() = repository.getComicData()

}