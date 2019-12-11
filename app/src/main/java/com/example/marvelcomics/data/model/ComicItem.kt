package com.example.marvelcomics.data.model

data class ComicItem(
    val id: Int = 0,
    val title: String = "Teste",
    val description: String = "Teste",
    val thumbnail: Thumbnail = Thumbnail("", ""),
    val pageCount: Int = 10,
    val resourceURI: String = "Teste"
)
