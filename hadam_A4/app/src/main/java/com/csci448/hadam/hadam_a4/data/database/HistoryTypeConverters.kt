package com.csci448.hadam.hadam_a4.data.database

import androidx.room.TypeConverter
import java.util.UUID

class HistoryTypeConverters {
    @TypeConverter
    fun fromUUID(uuid: UUID?) = uuid?.toString()

    @TypeConverter
    fun toUUID(uuid: String?) = UUID.fromString(uuid)
}