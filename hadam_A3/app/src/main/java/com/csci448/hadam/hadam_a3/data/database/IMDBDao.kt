package com.csci448.hadam.hadam_a3.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.csci448.hadam.hadam_a3.data.Video
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface IMDBDao {
    @Insert
    fun addVideo(video: Video)

    @Query("SELECT * FROM video")
    fun getVideos(): Flow<List<Video>>

    @Query("SELECT * FROM video WHERE id=(:id)")
    suspend fun getVideoById(id: String): Video?

    @Delete
    fun deleteVideo(video: Video)
}