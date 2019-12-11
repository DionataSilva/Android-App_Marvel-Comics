package com.example.marvelcomics.domain.usecases

import com.example.marvelcomics.data.model.Character
import com.example.marvelcomics.data.model.Data
import com.example.marvelcomics.domain.repository.CharacterRepository

class CharactersDataRequest(private val repository: CharacterRepository) {

    suspend operator fun invoke(offset: Int): Data<Character>? {
        return repository.requestCharactersData(offset)
    }

}