package com.csci448.hadam.hadam_a3.util.api

import com.csci448.hadam.hadam_a3.data.autocomplete.AutoComplete
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface IMDBApiService {
    companion object {
        const val BASE_API_URL = "https://imdb8.p.rapidapi.com"
    }

    @Headers(
        "X-RapidAPI-Key: 4aed1fa553msh9690f994a296a84p189f40jsn356f52792a59",
        "X-RapidAPI-Host: imdb8.p.rapidapi.com"
    )
    @GET("auto-complete")
    fun getVideo(@Query("q") searchText: String): Call<AutoComplete>
}