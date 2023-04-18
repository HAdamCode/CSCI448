package com.csci448.hadam.hadam_a3.data

import android.content.Context
import android.util.Log
import com.csci448.hadam.hadam_a3.data.database.IMDBDao
import com.csci448.hadam.hadam_a3.data.database.IMDBDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class IMDBRepo
private constructor(
    private val imdbDao: IMDBDao,
    private val coroutineScope: CoroutineScope = GlobalScope
) {
    companion object {
        private const val LOG_TAG = "448.IMDBRepo"
        private var INSTANCE: IMDBRepo? = null

        /**
         * @param context
         */
        fun getInstance(context: Context): IMDBRepo {
            var instance = INSTANCE
            if (instance == null) {
                val database = IMDBDatabase.getInstance(context)
                instance = IMDBRepo(database.imdbDao)
                INSTANCE = instance
            }
            return instance
        }
    }

    init {
        Log.d(LOG_TAG, "initializing repository list")
    }

    fun getVideos(): Flow<List<Video>> = imdbDao.getVideos()
    suspend fun getVideo(id: String): Video? = imdbDao.getVideoById(id)
    fun addVideo(video: Video) {
        coroutineScope.launch {
            imdbDao.addVideo(video)
        }
    }

    fun deleteVideo(video: Video) {
        coroutineScope.launch {
            imdbDao.deleteVideo(video)
        }
    }

    fun toggleFavorite(id: String, isFavorite: Boolean) {
        coroutineScope.launch {
            imdbDao.toggleFavorite(id, isFavorite)
        }
    }
}