package com.example.marvelcomics.domain.usecases

import com.example.marvelcomics.data.model.Data
import com.example.marvelcomics.domain.repository.DataRepository

class DataRequest(private val repository: DataRepository) {

    suspend operator fun invoke(offset: Int): Data? {
        return repository.getData(offset)
    }

}