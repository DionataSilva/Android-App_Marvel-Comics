package com.example.marvelcomics.data.model

data class ComicItem(
    val id: Int = 1,
    val title: String = "Comic Test",
    val description: String = "Description",
    val pageCount: Int = 0,
    val resourceURI: String = "http://gateway.marvel.com/v1/public/comics/183",
    val thumbnail: Thumbnail = Thumbnail()
)
