package com.csci448.hadam.hadam_a4.util.api

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.csci448.hadam.hadam_a4.data.ApiDataClass
import com.csci448.hadam.hadam_a4.data.History
import com.csci448.hadam.hadam_a4.data.Weather
import com.csci448.hadam.hadam_a4.presentation.viewmodel.IHistoryViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import okhttp3.internal.format
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query
import java.text.DecimalFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class WeatherQueryFetchr {
    companion object {
        private const val LOG_TAG = "448.Fetchr"
    }

    fun getWeather(
        latitude: Double,
        longitude: Double,
        apiKey: String,
        context: Context,
        historyViewModel: IHistoryViewModel,
        enableSave: Boolean
    ) {
//        if (searchText == null) {
//            mWeather.update { null }
//            return
//        }
//        Log.e(LOG_TAG, searchText)
        val imdbRequest = weatherApiService.getWeather(latitude, longitude, apiKey = apiKey)

        imdbRequest.enqueue(object : Callback<ApiDataClass> {
            override fun onFailure(call: Call<ApiDataClass>, t: Throwable) {
                Log.e(LOG_TAG, "onFailure() called $t")
            }


            override fun onResponse(
                call: Call<ApiDataClass>,
                response: Response<ApiDataClass>
            ) {
                Log.d(LOG_TAG, "onResponse() called")
                val responseWeather = response.body()
                if (responseWeather == null) {
                    Log.d(LOG_TAG, "response character is null")
                    mWeather.update { null }
                } else {
                    Log.d(LOG_TAG, responseWeather.weather.first().description)
                    mWeather.update { responseWeather }
                    val current =
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yy HH:mm"))
                    val temp = responseWeather.main.temp
                    val desc = responseWeather.weather.first().description
                    Toast.makeText(
                        context,
                        "Lat/Lng: (${DecimalFormat("#.##").format(latitude)}, ${
                            DecimalFormat("#.##").format(
                                longitude
                            )
                        })  " +
                                "$current\n" +
                                "Temp: $temp ($desc)",
                        Toast.LENGTH_LONG
                    ).show()
                    val historyToAdd = History(
                        lon = longitude,
                        lat = latitude,
                        temp = temp,
                        description = desc,
                        dateTime = current.toString()
                    )
                    if (enableSave)
                        historyViewModel.addHistory(historyToAdd)
                    historyViewModel.updateCurrentLocation(historyToAdd)
                }
            }
        })
    }

    //    fun resetWeather() {
//        mWeather.update { null }
//    }
    private val weatherApiService: WeatherApiService
    private val mWeather = MutableStateFlow<ApiDataClass?>(null)

    val weather: StateFlow<ApiDataClass?>
        get() = mWeather.asStateFlow()


    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(WeatherApiService.BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        weatherApiService = retrofit.create(WeatherApiService::class.java)
    }
}