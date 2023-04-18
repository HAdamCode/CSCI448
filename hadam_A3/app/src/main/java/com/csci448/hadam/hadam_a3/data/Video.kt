package com.csci448.hadam.hadam_a3.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "video")
data class Video(
    @PrimaryKey
    val id: String,
    val name: String,
    val rank: Int,
    val year: Int,
    val genre: String?,
    val actors: String,
    val imageUrl: String,
    val favorite: Boolean
)
