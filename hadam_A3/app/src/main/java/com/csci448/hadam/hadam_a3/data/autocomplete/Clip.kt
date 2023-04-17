package com.csci448.hadam.hadam_a3.data.autocomplete

import com.google.gson.annotations.SerializedName

data class Clip(
    val id: String,
    @SerializedName("l")
    val title: String,
    @SerializedName("s")
    val time: String,
    @SerializedName("i")
    val link: Link
)
