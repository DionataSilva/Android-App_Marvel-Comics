package com.example.marvelcomics.data.model

data class Comics(
    val available: Int = 1,
    val collectionURI: String = "",
    val items: List<ComicItem> = listOf()
)
