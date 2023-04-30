package com.csci448.hadam.hadam_a4.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.csci448.hadam.hadam_a4.data.History
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {
    @Insert
    fun addHistory(history: History)

    @Query("SELECT * FROM history")
    fun getHistories(): Flow<List<History>>

    @Query("SELECT * FROM history WHERE id=(:id)")
    suspend fun getHistoryById(id: String): History?

    @Delete
    fun deleteHistory(history: History)
}