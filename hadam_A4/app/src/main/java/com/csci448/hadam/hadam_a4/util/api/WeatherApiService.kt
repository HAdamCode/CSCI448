package com.csci448.hadam.hadam_a4.util.api

import com.csci448.hadam.hadam_a4.data.ApiDataClass
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    companion object {
        const val BASE_API_URL = "https://api.openweathermap.org/data/2.5/"
    }

    @GET("weather")
    fun getWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String = "imperial",
        @Query("appid") apiKey: String
    ): Call<ApiDataClass>
}