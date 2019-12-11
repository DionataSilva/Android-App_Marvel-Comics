package com.example.marvelcomics.domain.usecases

import com.example.marvelcomics.data.model.Character
import com.example.marvelcomics.domain.repository.CharacterRepository

class SaveCharacter(private val repositoryCharacters: CharacterRepository) {

    suspend operator fun invoke(character: Character) {
        repositoryCharacters.saveCharacter(character)
    }

}