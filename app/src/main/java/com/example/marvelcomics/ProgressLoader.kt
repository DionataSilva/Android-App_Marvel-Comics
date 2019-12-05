@file:Suppress("DEPRECATION")

package com.example.marvelcomics

import android.app.ProgressDialog
import android.content.Context
import com.example.marvelcomics.extentions.globalLaunch
import com.example.marvelcomics.helpers.CoroutinesContextProvider

object ProgressLoader {

    private var progress: ProgressDialog? = null
    private val contextProvider = CoroutinesContextProvider()

    fun call(context: Context?, message: String? = null) {
        context?.run {
            if (progress != null) dismiss()

            try {
                progress = ProgressDialog(this, R.style.ProgressDialog)
                progress?.setMessage(message ?: getString(R.string.loading))
                progress?.setCancelable(false)
                globalLaunch(contextProvider.main) { progress?.show() }
            } catch (ex: RuntimeException) {
                ex.printStackTrace()
            }
        }
    }

    fun dismiss() {
        try {
            progress?.dismiss()
        } catch (ex: RuntimeException) {
            ex.printStackTrace()
        }

        progress = null
    }

}