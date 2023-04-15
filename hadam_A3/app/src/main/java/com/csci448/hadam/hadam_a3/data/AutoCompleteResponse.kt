package com.csci448.hadam.hadam_a3.data

import com.google.gson.annotations.SerializedName

data class AutoCompleteResponse(
    @SerializedName("d")
    val suggestions: List<AutoCompleteSuggestion>?,
    @SerializedName("q")
    val query: String?,
    @SerializedName("v")
    val version: Int?
)


data class Suggestion(
    @SerializedName("id")
    val id: String?,
    @SerializedName("l")
    val title: String?,
    @SerializedName("s")
    val year: String?,
    @SerializedName("i")
    val image: String?
)

data class TitleVideosResponse(
    @SerializedName("id")
    val id: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("video")
    val video: Video?,
    @SerializedName("year")
    val year: String?,
    @SerializedName("plot")
    val plot: String?
)

data class Video(
    @SerializedName("title")
    val title: String?,
    @SerializedName("embed")
    val embed: String?
)