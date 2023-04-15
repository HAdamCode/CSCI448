package com.csci448.hadam.hadam_a3.data

import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

object RetrofitClient {
    private const val BASE_URL = "https://imdb-api.com/"
    private const val API_KEY = "YOUR_RAPID_API_KEY" // Replace with your actual Rapid API key

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor(API_KEY))
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    val autoCompleteService: AutoCompleteService by lazy {
        retrofit.create(AutoCompleteService::class.java)
    }

    val titleVideosService: TitleVideosService by lazy {
        retrofit.create(TitleVideosService::class.java)
    }
}

interface AutoCompleteService {
    @GET("en/API/AutoComplete")
    suspend fun getAutoCompleteSuggestions(
        @Header("x-rapidapi-key") apiKey: String,
        @Query("q") query: String
    ): Response<AutoCompleteResponse>
}

interface TitleVideosService {
    @GET("en/API/Videos")
    suspend fun getTitleVideos(
        @Header("x-rapidapi-key") apiKey: String,
        @Query("id") id: String,
        @Query("type") type: String
    ): Response<TitleVideosResponse>
}
