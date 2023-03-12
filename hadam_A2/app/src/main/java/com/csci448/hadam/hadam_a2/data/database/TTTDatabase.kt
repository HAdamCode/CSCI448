package com.csci448.hadam.hadam_a2.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.csci448.hadam.hadam_a2.data.TTTGame

@Database(entities = [TTTGame::class], version = 1)
@TypeConverters(TTTTypeConverters::class)
abstract class TTTDatabase : RoomDatabase() {
    companion object {
        @Volatile
        private var INSTANCE: TTTDatabase? = null
        fun getInstance(context: Context): TTTDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context, TTTDatabase::class.java,
                        "game-database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

    abstract val tttDao: TTTDao
}