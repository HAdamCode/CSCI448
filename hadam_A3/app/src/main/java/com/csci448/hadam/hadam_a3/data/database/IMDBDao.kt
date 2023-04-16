package com.csci448.hadam.hadam_a3.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.csci448.hadam.hadam_a3.data.titlevideo.TitleVideo
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface IMDBDao {
    @Insert
    fun addVideo(titleVideo: TitleVideo)

    @Query("SELECT * FROM titlevideo")
    fun getVideos(): Flow<List<TitleVideo>>

    @Query("SELECT * FROM titlevideo WHERE id=(:id)")
    suspend fun getVideoById(id: UUID): TitleVideo?

    @Delete
    fun deleteVideo(titleVideo: TitleVideo)
}