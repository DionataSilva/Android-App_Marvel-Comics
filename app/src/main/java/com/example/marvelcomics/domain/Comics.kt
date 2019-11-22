package com.example.marvelcomics.domain

class Comics(
    val available: Int = 1,
    val collectionURI: String = "",
    val items: List<ComicItem> = listOf()
)
