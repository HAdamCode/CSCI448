package com.csci448.hadam.hadam_a3.data.autocomplete

import com.google.gson.annotations.SerializedName

data class Movies(
    @SerializedName("i")
    val link: Link?,
    val id: String,
    @SerializedName("l")
    val movie: String,
    @SerializedName("q")
    val type: String,
    val rank: Int,
    @SerializedName("s")
    val starts: String,
    @SerializedName("v")
    val clips: List<Clip>,
    val vt: Int,
    @SerializedName("y")
    val year: Int,
    @SerializedName("yr")
    val yearRange: String
)
