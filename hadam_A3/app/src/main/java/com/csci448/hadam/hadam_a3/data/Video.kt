package com.csci448.hadam.hadam_a3.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "video")
data class Video(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    val name: String
)
