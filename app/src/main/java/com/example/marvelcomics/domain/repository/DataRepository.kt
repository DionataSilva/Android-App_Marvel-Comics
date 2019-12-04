package com.example.marvelcomics.domain.repository

import com.example.marvelcomics.data.model.Data

interface DataRepository {

    suspend fun getData(offset: Int) : Data?

}