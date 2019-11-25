package com.example.marvelcomics.domain

class Data (
    val offset: Int = 0,
    val limit: Int = 0,
    val total: Int = 0,
    val count: Int = 0,
    val  results: List<Character>
)