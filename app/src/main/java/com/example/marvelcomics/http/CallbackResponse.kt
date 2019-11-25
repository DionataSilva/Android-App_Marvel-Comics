package com.example.marvelcomics.http

interface CallbackResponse<T> {
    fun sucess(response: T)
    fun fail(error: String)
}