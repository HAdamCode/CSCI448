package com.csci448.hadam.hadam_a3.util.api

import android.util.Log
import com.csci448.hadam.hadam_a3.data.autocomplete.AutoComplete
import com.csci448.hadam.hadam_a3.data.titlevideo.TitleVideo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class IMDBQueryFetchr {
    companion object {
        private const val LOG_TAG = "448.Fetchr"
    }

    fun getVideo(searchText: String?) {
        if (searchText == null) {
            mAutoComplete.update { null }
            return
        }
        Log.e(LOG_TAG, searchText)
        val imdbRequest = imdbApiService.getVideo(searchText)

        imdbRequest.enqueue(object : Callback<AutoComplete> {
            override fun onFailure(call: Call<AutoComplete>, t: Throwable) {
                Log.e(LOG_TAG, "onFailure() called $t")
            }


            override fun onResponse(
                call: Call<AutoComplete>,
                response: Response<AutoComplete>
            ) {
                Log.d(LOG_TAG, "onResponse() called")
                val responseCharacter = response.body()
                if (responseCharacter == null) {
                    Log.d(LOG_TAG, "response character is null")
                    mAutoComplete.update { null }
                } else {
                    Log.d(LOG_TAG, responseCharacter.name)
                    mAutoComplete.update { responseCharacter }
                }
            }
        })
    }

    fun getTitleVideo(id: String) {
        Log.d(LOG_TAG, id)
        mTitleVideo.update { null }
        val imdbRequest = imdbApiService.findVideo(id)

        imdbRequest.enqueue(object : Callback<TitleVideo> {
            override fun onFailure(call: Call<TitleVideo>, t: Throwable) {
                Log.e(LOG_TAG, "onFailure() called $t")
            }


            override fun onResponse(
                call: Call<TitleVideo>,
                response: Response<TitleVideo>
            ) {
                Log.d(LOG_TAG, "onResponse() called")
                val responseTitleVideo = response.body()
                if (responseTitleVideo == null) {
                    Log.d(LOG_TAG, "resource is null")
                    mTitleVideo.update { null }
                    mBadQuery.update { true }
                } else {
                    Log.d(LOG_TAG, responseTitleVideo.resource.title)
                    mTitleVideo.update { responseTitleVideo }
                }
            }
        })
    }

    fun resetAutoComplete() {
        mAutoComplete.update { null }
    }
    private val imdbApiService: IMDBApiService
    private val mAutoComplete = MutableStateFlow<AutoComplete?>(null)
    private val mTitleVideo = MutableStateFlow<TitleVideo?>(null)
    private val mBadQuery = MutableStateFlow<Boolean>(false)

    val AutoComplete: StateFlow<AutoComplete?>
        get() = mAutoComplete.asStateFlow()

    val TitleVideo: StateFlow<TitleVideo?>
        get() = mTitleVideo.asStateFlow()

    val BadQuery: StateFlow<Boolean?>
        get() = mBadQuery.asStateFlow()

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(IMDBApiService.BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        imdbApiService = retrofit.create(IMDBApiService::class.java)
    }
}