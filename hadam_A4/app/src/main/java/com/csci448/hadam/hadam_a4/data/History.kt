package com.csci448.hadam.hadam_a4.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "history")
data class History(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    val lon: Double,
    val lat: Double,
    val temp: Float,
    val description: String,
    val dateTime: String
)
