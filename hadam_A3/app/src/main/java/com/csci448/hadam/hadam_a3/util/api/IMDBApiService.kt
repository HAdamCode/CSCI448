package com.csci448.hadam.hadam_a3.util.api

import retrofit2.Call
import com.csci448.hadam.hadam_a3.data.titlevideo.TitleVideo
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface IMDBApiService {
    companion object {
        const val BASE_API_URL = "https://imdb8.p.rapidapi.com"
        const val OMBD_API_KEY = "229e5323"
    }

//    @Headers(
////        "X-RapidAPI-Key: 4aed1fa553msh9690f994a296a84p189f40jsn356f52792a59",
////        "X-RapidAPI-Host: imdb8.p.rapidapi.com"
////    )
////    @GET("auto-complete")
////    fun getTitleVideo(): Call<TitleVideo>
    @GET("/")
    fun getTitleVideo(@Query("s") searchText: String, @Query("apikey") ombd_api_key: String = OMBD_API_KEY): Call<TitleVideo>
}