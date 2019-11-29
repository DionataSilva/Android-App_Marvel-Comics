package com.example.marvelcomics.data.service.character

import android.util.Log
import com.example.marvelcomics.data.service.CallbackResponse
import com.example.marvelcomics.data.service.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterWebClient {

    fun list(offset: Int, callbackResponse: CallbackResponse<com.example.marvelcomics.data.model.Response>) {
        val call = RetrofitInitializer().characterService().list(offset)

        call.enqueue(
            object : Callback<com.example.marvelcomics.data.model.Response> {

                override fun onResponse(
                    call: Call<com.example.marvelcomics.data.model.Response>,
                    response: Response<com.example.marvelcomics.data.model.Response>
                ) {
                    response.body()?.let {
                        callbackResponse.sucess(it)
                    }
                }

                override fun onFailure(
                    call: Call<com.example.marvelcomics.data.model.Response>,
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