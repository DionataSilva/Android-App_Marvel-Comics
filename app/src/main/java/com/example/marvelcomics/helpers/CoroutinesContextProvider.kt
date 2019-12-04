package com.example.marvelcomics.helpers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

open class CoroutinesContextProvider {
    open val main: CoroutineDispatcher by lazy { Dispatchers.Main }
    open val default: CoroutineDispatcher by lazy { Dispatchers.Default }
    open val io: CoroutineDispatcher by lazy { Dispatchers.IO }
}
