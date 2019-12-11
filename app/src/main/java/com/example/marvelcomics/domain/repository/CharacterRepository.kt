package com.example.marvelcomics.domain.repository

import com.example.marvelcomics.data.model.Character
import com.example.marvelcomics.data.model.Data

interface CharacterRepository {

    suspend fun requestCharactersData(offset: Int) : Data<Character>?

    // suspend fun saveCharacterData(characterData: Data<Character>) TODO: Implementar desta forma

    suspend fun saveCharacter(character: Character)

    suspend fun getCharacter() : Character

}