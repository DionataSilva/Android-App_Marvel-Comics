package com.example.marvelcomics.extentions

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun globalLaunch(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job {
    return GlobalScope.launch(context, start, block)
}

fun <T> globalAsync(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> T
): Deferred<T> {
    return GlobalScope.async(context, start, block)
}

suspend fun <T> retry(
    times: Int = 3,
    delayTime: Long = 2000,
    block: suspend () -> T,
    error: (ex: Exception)-> Unit
): T {
    repeat(times - 1) {
        try {
            return block()
        } catch (e: Exception) {
            error(e)
        }
        delay(delayTime)
    }
    return block()
}