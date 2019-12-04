package com.example.marvelcomics.ui.abstracts

import com.example.marvelcomics.extentions.globalLaunch
import com.example.marvelcomics.helpers.CoroutinesContextProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext

abstract class BasePresenter{

    private var contextProvider = CoroutinesContextProvider()
    private val asyncJobs: MutableList<Job> = mutableListOf()
    private var firstSession = true

    fun start() {
        if (firstSession) {
            firstSession = false
            onStart()
        } else {
            onRestart()
        }
        postStart()
    }

    open fun onDestroyView() {
        cancelAllAsync()
    }

    fun launchUI(block: suspend CoroutineScope.() -> Unit) {
        val job: Job = globalLaunch(contextProvider.main) {
            block()
        }
        asyncJobs.add(job)
        job.invokeOnCompletion {
            asyncJobs.remove(job)
        }
    }

    fun launchDefault(block: suspend CoroutineScope.() -> Unit) {
        val job: Job = globalLaunch(contextProvider.default) {
            block()
        }
        asyncJobs.add(job)
        job.invokeOnCompletion {
            asyncJobs.remove(job)
        }
    }

    private fun cancelAllAsync() {
        val asyncJobsSize = asyncJobs.size

        if (asyncJobsSize > 0) {
            for (i in asyncJobsSize - 1 downTo 0) {
                asyncJobs[i].cancel()
            }
        }
    }

    open fun onStart() {}
    open fun onRestart() {}
    open fun postStart() {}

    suspend fun <T> CoroutineScope.withDefault(block: suspend () -> T): T {
        return withContext(contextProvider.default) { block() }
    }

}