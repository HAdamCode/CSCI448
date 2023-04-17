package com.csci448.hadam.hadam_a3.util.api

import retrofit2.Call
import com.csci448.hadam.hadam_a3.data.titlevideo.TitleVideo
import retrofit2.http.GET


interface IMDBApiService {
    companion object {
        const val BASE_API_URL = "https://cs-courses.mines.edu"
    }

    @GET("csci448/samodelkin/")
    fun getTitleVideo(): Call<TitleVideo>
}