package com.example.marvelcomics.http

import android.util.Log
import com.example.marvelcomics.domain.Character
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterWebClient {

    fun list(offset: Int, callbackResponse: CallbackResponse<com.example.marvelcomics.domain.Response>) {
        val call = RetrofitInitializer().characterService().list(offset)

        call.enqueue(
            object : Callback<com.example.marvelcomics.domain.Response> {

                override fun onResponse(
                    call: Call<com.example.marvelcomics.domain.Response>,
                    response: Response<com.example.marvelcomics.domain.Response>
                ) {
                    response.body()?.let {
                        callbackResponse.sucess(it)
                    }
                }

                override fun onFailure(
                    call: Call<com.example.marvelcomics.domain.Response>,
                    t: Throwable
                ) {
                    Log.e("onFailure error", t.message)
                    t.message?.let {
                        callbackResponse.fail(it)
                    }
                }
            }
        )
    }
}