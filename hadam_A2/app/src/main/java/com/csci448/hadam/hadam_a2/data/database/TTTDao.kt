package com.csci448.hadam.hadam_a2.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.csci448.hadam.hadam_a2.data.TTTGame
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface TTTDao {

    @Insert
    fun addGame(tttGame: TTTGame)

    @Query("SELECT * FROM game")
    fun getGame(): Flow<List<TTTGame>>

    @Query("SELECT * FROM game WHERE id=(:id)")
    suspend fun getGameById(id: UUID): TTTGame?

    @Delete
    fun deleteGame(tttGame: TTTGame)
}