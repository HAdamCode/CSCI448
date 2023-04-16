package com.csci448.hadam.hadam_a3.util.api

import android.util.Log
import com.csci448.hadam.hadam_a3.data.titlevideo.TitleVideo
import com.csci448.hadam.hadam_a3.util.api.IMDBApiService
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
import java.util.*

abstract class IMDBFetchr {
    companion object {
        private const val LOG_TAG = "448.Fetchr"
    }

//    fun getTitleVideo() {
//        val imdbRequest = imdbApiService.getTitleVideo()
//        imdbRequest.enqueue(object : Callback<TitleVideo> {
//            override fun onFailure(call: Call<TitleVideo>, t: Throwable) {
//                Log.e(LOG_TAG, "onFailure() called $t")
//                // TODO – finish in Part 2.V.A
//            }
//
//            override fun onResponse(
//                call: Call<TitleVideo>,
//                response: Response<TitleVideo>
//            ) {
//                Log.d(LOG_TAG, "onResponse() called")
////                val responseTitleVideo = response.body()
////                if (responseTitleVideo == null) {
////                    Log.d(LOG_TAG, "response character is null")
////                    mTitleVideo.update { null }
////                } else {
////                    val newTitleVideo = responseTitleVideo.copy(
//////                        meta = "file:///android_asset/characters/${responseTitleVideo.meta}",
//////                        id = UUID.randomUUID()
////                        //TODO Fix this
////                    )
//////                    Log.d(LOG_TAG, newTitleVideo.name)
////                    mTitleVideo.update { newTitleVideo }
////                }
//                val client = OkHttpClient()
//
//                val request = Request.Builder()
//                    .url("https://imdb8.p.rapidapi.com/title/get-videos?tconst=tt0944947&limit=25&region=US")
//                    .get()
//                    .addHeader("X-RapidAPI-Key", "4aed1fa553msh9690f994a296a84p189f40jsn356f52792a59")
//                    .addHeader("X-RapidAPI-Host", "imdb8.p.rapidapi.com")
//                    .build()
//
//                val response = client.newCall(request).execute()
////                val responseTitleVideo: List<TitleVideo> = response.body()
//
//                // assign value to viewmodel entity
////                mTitleVideo.update { response.body() }
//            }
//        })
//
//    }

        private val imdbApiService: IMDBApiService
    private val mTitleVideo = MutableStateFlow<TitleVideo?>(null)

    val titleVideo: StateFlow<TitleVideo?>
        get() = mTitleVideo.asStateFlow()

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(IMDBApiService.BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        imdbApiService = retrofit.create(IMDBApiService::class.java)
    }
}