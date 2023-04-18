package com.csci448.hadam.hadam_a3.data.autocomplete

import com.google.gson.annotations.SerializedName

data class AutoComplete(
    @SerializedName("d")
    val movies: List<Movies>,
    @SerializedName("q")
    val name: String,
)
