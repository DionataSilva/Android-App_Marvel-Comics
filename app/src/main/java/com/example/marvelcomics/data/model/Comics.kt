package com.example.marvelcomics.data.model

data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: List<ComicItem>
)
