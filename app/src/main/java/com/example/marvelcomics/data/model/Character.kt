package com.example.marvelcomics.data.model

class Character(
    val id: Int = 1,
    val name: String = "Character test",
    val description: String = "Character test",
    val thumbnail: Thumbnail = Thumbnail(),
    val resourceURI: String = "http://gateway.marvel.com/v1/public/characters/1011334",
    val comics: Comics = Comics(
        items = listOf(
            ComicItem(),
            ComicItem(),
            ComicItem(),
            ComicItem(),
            ComicItem(),
            ComicItem(),
            ComicItem(),
            ComicItem(),
            ComicItem()
        )
    )
)