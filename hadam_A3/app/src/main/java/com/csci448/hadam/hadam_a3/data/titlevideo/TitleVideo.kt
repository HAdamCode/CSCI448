package com.csci448.hadam.hadam_a3.data.titlevideo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "titlevideo")
data class TitleVideo(
    val meta: Meta?,
    val resource: Resource?,
    @PrimaryKey
    val id: UUID = UUID.randomUUID()
)
