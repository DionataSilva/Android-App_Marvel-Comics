package com.example.marvelcomics.domain
class Character(
    val id: Int,
    val name: String,
    val descrtion: String,
    val thumbnail: Thumbnail,
    val resourceURI: String,
    val comics: Comics
)