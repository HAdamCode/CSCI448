package com.csci448.hadam.hadam_a2.data

import android.content.Context
import android.util.Log
import com.csci448.hadam.hadam_a2.data.database.TTTDao
import com.csci448.hadam.hadam_a2.data.database.TTTDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.*

class TTTRepo
private constructor(
    private val tttDao: TTTDao,
    private val coroutineScope: CoroutineScope = GlobalScope
) {
    companion object {
        private const val LOG_TAG = "448.TTTRepo"
        private var INSTANCE: TTTRepo? = null

        fun getInstance(context: Context): TTTRepo {
            var instance = INSTANCE
            if (instance == null) {
                val database = TTTDatabase.getInstance(context)
                instance = TTTRepo(database.tttDao)
                INSTANCE = instance
            }
            return instance
        }
    }

    init {
        Log.d(LOG_TAG, "initializing repository list")
    }

    fun getGames(): Flow<List<TTTGame>> = tttDao.getGame()
    suspend fun getGame(id: UUID): TTTGame? = tttDao.getGameById(id)
    fun addGame(tttGame: TTTGame) {
        coroutineScope.launch {
            tttDao.addGame(tttGame)
        }
    }

    fun deleteCharacter(tttGame: TTTGame) {
        coroutineScope.launch {
            tttDao.deleteGame(tttGame)
        }
    }
}