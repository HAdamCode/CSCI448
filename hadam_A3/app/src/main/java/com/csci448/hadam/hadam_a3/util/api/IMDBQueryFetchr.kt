package com.csci448.hadam.hadam_a3.util.api

import android.util.Log
import com.csci448.hadam.hadam_a3.data.titlevideo.TitleVideo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class IMDBQueryFetchr {
    companion object {
        private const val LOG_TAG = "448.Fetchr"
    }

    fun getTitleVideo() {
        Log.e(LOG_TAG, "onFailure() called")
        val imdbRequest = imdbApiService.getTitleVideo("Game of Th")

        imdbRequest.enqueue(object : Callback<TitleVideo> {
            override fun onFailure(call: Call<TitleVideo>, t: Throwable) {
                Log.e(LOG_TAG, "onFailure() called $t")
            }


            override fun onResponse(
                call: Call<TitleVideo>,
                response: Response<TitleVideo>
            ) {
                Log.d(LOG_TAG, "onResponse() called")
                val allSearch = response.body()
                allSearch?.let {
//                    for (movie in allSearch) {
//                        if (movie.title != null && movie.poster != null) {
//
//                        }
//                        Log.i("Response : ", "$movie")
//                    }
                    mTitleVideo.update { allSearch }
//                    recyclerView_mainActivity.adapter?.notifyDataSetChanged()
                }
                if (allSearch != null) {
                    Log.d(LOG_TAG, allSearch.resource.title)
                }
                else {
                    Log.d(LOG_TAG, "onResponse() failed inside me")

                }
//                mTitleVideo.update { response.body() }
            }
        })

    }

    private val imdbApiService: IMDBApiService
    private val mTitleVideo = MutableStateFlow<TitleVideo?>(null)

    val titleVideo: StateFlow<TitleVideo?>
        get() = mTitleVideo.asStateFlow()

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.omdbapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        imdbApiService = retrofit.create(IMDBApiService::class.java)
    }
}