package com.csci448.hadam.hadam_a3.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.csci448.hadam.hadam_a3.data.Video

@Database(entities = [Video::class], version = 1)
@TypeConverters(IMDBTypeConverters::class)
abstract class IMDBDatabase : RoomDatabase() {
    companion object {
        @Volatile
        private var INSTANCE: IMDBDatabase? = null
        fun getInstance(context: Context): IMDBDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context, IMDBDatabase::class.java,
                        "imdb-database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

    abstract val imdbDao: IMDBDao
}