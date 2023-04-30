package com.csci448.hadam.hadam_a4.data

import android.location.Location
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class History(
    @PrimaryKey
    val id: String,
//    val location: Location,

//    val name: String,
//    val rank: Int,
//    val year: Int,
//    val genre: String?,
//    val actors: String,
//    val imageUrl: String,
//    val favorite: Boolean
)
