package com.example.marvelcomics.data.repository

import android.util.Log
import com.example.marvelcomics.data.model.Character
import com.example.marvelcomics.data.model.Data
import com.example.marvelcomics.data.service.RetrofitInitializer
import com.example.marvelcomics.domain.repository.CharacterRepository

class CharacterRepositoryImpl : CharacterRepository {

    private val api = RetrofitInitializer().service()
    private lateinit var character: Character

    override suspend fun saveCharacter(character: Character) {
        this.character = character
    }

    override suspend fun getCharacter() = this.character

    override suspend fun requestCharactersData(offset: Int): Data<Character>? {
        try {
            val charactersResponse = api.getCharactersData(offset)
            Log.e("apiResponse", "$charactersResponse") // TODO: Remover ao final
            return charactersResponse.data
        } catch (e: Exception) {
            Log.e("Exception", e.message) // TODO: Remover ao final
            throw Exception(e.message)
        }
    }
}
