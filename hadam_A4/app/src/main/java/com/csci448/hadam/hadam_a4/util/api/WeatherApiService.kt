package com.csci448.hadam.hadam_a4.util.api

import com.csci448.hadam.hadam_a4.data.Weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WeatherApiService {
    companion object {
        const val BASE_API_URL = "https://api.openweathermap.org/data/2.5/"
    }

//    @Headers(
//        "X-RapidAPI-Key: ed3aa478701fd9607baee19c2d61f423",
//        "X-RapidAPI-Host: imdb8.p.rapidapi.com"
//    )
//@GET("weather")
//suspend fun getWeather(
//    @Query("lat") latitude: Double,
//    @Query("lon") longitude: Double,
//    @Query("units") units: String = "metric",
//    @Query("appid") apiKey: String
//): WeatherResponse
    @GET("weather")
    fun getWeather(@Query("lat") latitude: Double,
                   @Query("lon") longitude: Double,
                   @Query("units") units: String = "metric",
                   @Query("appid") apiKey: String): Call<Weather>
}