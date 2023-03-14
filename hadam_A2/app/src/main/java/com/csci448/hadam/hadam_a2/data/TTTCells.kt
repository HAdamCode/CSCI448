package com.csci448.hadam.hadam_a2.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

data class TTTCells (
    val row: Int,
    val col: Int,
    var imageId: Int? = null
)

