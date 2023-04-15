package com.csci448.hadam.hadam_a3.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "autoCompleteSuggestions")
data class AutoCompleteSuggestion(
    @PrimaryKey
    val id: String,
    val title: String,
    val year: String,
    val image: String
)

@Entity(tableName = "titleVideos")
data class TitleVideo(
    @PrimaryKey
    val id: String,
    val title: String,
    val embed: String
)