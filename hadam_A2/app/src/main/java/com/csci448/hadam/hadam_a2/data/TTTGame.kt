package com.csci448.hadam.hadam_a2.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "game")
data class TTTGame(
    val winner: String,
    val gameType: String,
    @PrimaryKey
    val id: UUID = UUID.randomUUID()
)

// Add stuff
