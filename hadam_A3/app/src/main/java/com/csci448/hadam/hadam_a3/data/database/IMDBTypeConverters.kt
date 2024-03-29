package com.csci448.hadam.hadam_a3.data.database

import androidx.room.TypeConverter
import java.util.*

class IMDBTypeConverters {
    @TypeConverter
    fun fromUUID(uuid: UUID?) = uuid?.toString()

    @TypeConverter
    fun toUUID(uuid: String?) = UUID.fromString(uuid)
}
