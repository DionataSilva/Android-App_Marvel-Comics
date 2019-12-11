package com.example.marvelcomics.domain.usecases

import com.example.marvelcomics.domain.repository.CharacterRepository

class GetCharacter(private val repositoryCharacters: CharacterRepository) {

    suspend operator fun invoke() = repositoryCharacters.getCharacter()

}