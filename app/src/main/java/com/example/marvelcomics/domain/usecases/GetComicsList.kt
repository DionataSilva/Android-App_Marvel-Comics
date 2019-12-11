package com.example.marvelcomics.domain.usecases

import com.example.marvelcomics.domain.repository.ComicRepository

class GetComicsList(private val repository: ComicRepository) {

    suspend operator fun invoke() = repository.getComicsList()

}