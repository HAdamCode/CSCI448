package com.csci448.hadam.hadam_a4.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.csci448.hadam.hadam_a4.data.History

@Database(entities = [History::class], version = 1)
@TypeConverters(HistoryTypeConverters::class)
abstract class HistoryDatabase : RoomDatabase() {
    companion object {
        @Volatile
        private var INSTANCE: HistoryDatabase? = null
        fun getInstance(context: Context): HistoryDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context, HistoryDatabase::class.java,
                        "history-database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

    abstract val historyDao: HistoryDao
}