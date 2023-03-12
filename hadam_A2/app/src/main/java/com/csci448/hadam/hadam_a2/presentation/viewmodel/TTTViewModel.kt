package com.csci448.hadam.hadam_a2.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csci448.hadam.hadam_a2.data.TTTGame
import com.csci448.hadam.hadam_a2.data.TTTRepo
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*

class TTTViewModel(private val tttRepo: TTTRepo) : ViewModel(),
    ITTTViewModel {
    companion object {
        private const val LOG_TAG = "448.SamodelkinViewModel"
    }

    private val mGames: MutableStateFlow<List<TTTGame>> =
        MutableStateFlow(emptyList())


    /**
     * holds list of all characters stored within the view model
     */
    override val gameListState: StateFlow<List<TTTGame>>
        get() = mGames.asStateFlow()

    private val mCurrentGameState: MutableStateFlow<TTTGame?> =
        MutableStateFlow(null)

    override val currentGameState: StateFlow<TTTGame?>
        get() = mCurrentGameState.asStateFlow()

    private val mCurrentGameIdState: MutableStateFlow<UUID> =
        MutableStateFlow(UUID.randomUUID())

//    private val mCurrentNumGamesState: MutableStateFlow<Int?> =
//        MutableStateFlow(null)
//
//    override val currentNumGamesState: StateFlow<Int?>
//        get() = mCurrentNumGamesState.asStateFlow()

    init {
        viewModelScope.launch {
            tttRepo.getGames().collect { characterList ->
                mGames.update { characterList }
            }
        }
        viewModelScope.launch {
            mCurrentGameIdState
                .map { uuid -> tttRepo.getGame(uuid) }
                .collect { game -> mCurrentGameState.update { game } }
        }
//        viewModelScope.launch {
//            tttRepo.getNumWins().collect { mCurrentNumGamesState.update { 0 } }
//        }
    }

    override fun loadGameByUUID(uuid: UUID) {
        Log.d(LOG_TAG, "loadGameByUUID($uuid)")
        mCurrentGameIdState.update { uuid }
        return
    }

    override fun addGame(gameToAdd: TTTGame) {
        Log.d(LOG_TAG, "adding game $gameToAdd")
        tttRepo.addCharacter(gameToAdd)
    }

    override fun deleteGame(gameToDelete: TTTGame) {
        Log.d(LOG_TAG, "deleting game $gameToDelete")
        tttRepo.deleteCharacter(gameToDelete)
    }

//    override fun getNumWins() {
//        Log.d(LOG_TAG, "incrementing number of wins")
//        tttRepo.getNumWins()
//    }
}