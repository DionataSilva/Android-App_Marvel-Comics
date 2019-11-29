package com.example.marvelcomics.data.service

interface CallbackResponse<T> {
    fun sucess(response: T)
    fun fail(error: String)
}