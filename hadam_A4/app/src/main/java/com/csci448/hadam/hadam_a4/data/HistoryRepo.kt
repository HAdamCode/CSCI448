package com.csci448.hadam.hadam_a4.data

import android.content.Context
import android.util.Log
import com.csci448.hadam.hadam_a4.data.database.HistoryDao
import com.csci448.hadam.hadam_a4.data.database.HistoryDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HistoryRepo
private constructor(
    private val historyDao: HistoryDao,
    private val coroutineScope: CoroutineScope = GlobalScope
){
    companion object {
        private const val LOG_TAG = "448.IMDBRepo"
        private var INSTANCE: HistoryRepo? = null

        /**
         * @param context
         */
        fun getInstance(context: Context): HistoryRepo {
            var instance = INSTANCE
            if (instance == null) {
                val database = HistoryDatabase.getInstance(context)
                instance = HistoryRepo(database.historyDao)
                INSTANCE = instance
            }
            return instance
        }
    }

    init {
        Log.d(LOG_TAG, "initializing repository list")
    }

    fun getHistories(): Flow<List<History>> = historyDao.getHistories()
    suspend fun getHistory(id: String): History? = historyDao.getHistoryById(id)
    fun addHistory(history: History) {
        coroutineScope.launch {
            historyDao.addHistory(history)
        }
    }

    fun deleteHistory(history: History) {
        coroutineScope.launch {
            historyDao.deleteHistory(history)
        }
    }

//    fun toggleFavorite(id: String, isFavorite: Boolean) {
//        coroutineScope.launch {
//            historyDao.toggleFavorite(id, isFavorite)
//        }
//    }
}