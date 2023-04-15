package com.csci448.hadam.hadam_a3.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.csci448.hadam.hadam_a3.data.AutoCompleteSuggestion
import com.csci448.hadam.hadam_a3.data.TitleVideo

@Database(
    entities = [AutoCompleteSuggestion::class, TitleVideo::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun autoCompleteSuggestionDao(): IMDBDao.AutoCompleteSuggestionDao
    abstract fun titleVideoDao(): IMDBDao.TitleVideoDao
}