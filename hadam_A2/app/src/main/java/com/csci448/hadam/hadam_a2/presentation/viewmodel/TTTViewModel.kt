package com.csci448.hadam.hadam_a2.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csci448.hadam.hadam_a2.data.TTTCells
import com.csci448.hadam.hadam_a2.data.TTTGame
import com.csci448.hadam.hadam_a2.data.TTTRepo
import com.csci448.hadam.hadam_a2.presentation.game.Cell
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*

class TTTViewModel(private val tttRepo: TTTRepo) : ViewModel(),
    ITTTViewModel {
    companion object {
        private const val LOG_TAG = "448.TTTViewModel"
    }

    private val mGames: MutableStateFlow<List<TTTGame>> =
        MutableStateFlow(emptyList())

    override val gameListState: StateFlow<List<TTTGame>>
        get() = mGames.asStateFlow()

    private val mCurrentGameState: MutableStateFlow<TTTGame?> =
        MutableStateFlow(null)

    override val currentGameState: StateFlow<TTTGame?>
        get() = mCurrentGameState.asStateFlow()

    private val mCurrentGameIdState: MutableStateFlow<UUID> =
        MutableStateFlow(UUID.randomUUID())

    override val mOnePlayerGameCheck: MutableState<Boolean> = mutableStateOf(true)
    override val mDifficultyCheck: MutableState<Boolean> = mutableStateOf(false)
    override val mXGoesFirst: MutableState<Boolean> = mutableStateOf(true)
    override val mExistsWinner: MutableState<Boolean> = mutableStateOf(false)
    override val mWinner: MutableState<Int> = mutableStateOf(0)
    override val mTurn: MutableState<Int> = mutableStateOf(0)
    override val cells: MutableList<TTTCells> = mutableListOf()

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
        for (i in 0..2) {
            for (j in 0..2) {
                cells.add(TTTCells(i, j, null))
            }
        }
    }

    override fun loadGameByUUID(uuid: UUID) {
        Log.d(LOG_TAG, "loadGameByUUID($uuid)")
        mCurrentGameIdState.update { uuid }
        return
    }

    override fun addGame(gameToAdd: TTTGame) {
        Log.d(LOG_TAG, "adding game $gameToAdd")
        tttRepo.addGame(gameToAdd)
    }

    override fun deleteGame(gameToDelete: TTTGame) {
        Log.d(LOG_TAG, "deleting game $gameToDelete")
        tttRepo.deleteCharacter(gameToDelete)
    }

    override fun changeImage(imageId: Int, cellPostition: Int) {
        cells[cellPostition].imageId = imageId
    }

    override fun resetGame() {
        var gameType: String
        if (mOnePlayerGameCheck.value) {
            gameType = "One Player"
            if (!mDifficultyCheck.value) {
                gameType += " (Easy)"
            }
            else {
                gameType += " (Hard)"
            }
        }
        else {
            gameType = "Two Player"
        }
        if (mOnePlayerGameCheck.value) {
            if (mWinner.value == 1) {
                addGame(TTTGame("Player 1", gameType, UUID.randomUUID()))
            }
            else if (mWinner.value == 2){
                addGame(TTTGame("Computer", gameType, UUID.randomUUID()))
            }
            else {
                addGame(TTTGame("Tie", gameType, UUID.randomUUID()))
            }
        }
        else {
            if (mWinner.value == 1) {
                addGame(TTTGame("Player 1", gameType, UUID.randomUUID()))
            }
            else if (mWinner.value == 2){
                addGame(TTTGame("Player 2", gameType, UUID.randomUUID()))
            }
            else {
                addGame(TTTGame("Tie", gameType, UUID.randomUUID()))
            }
        }


        for (i in 0..8) {
            cells[i].imageId = null
        }
        mExistsWinner.value = false
        mTurn.value = 0

    }
//    override fun getNumWins() {
//        Log.d(LOG_TAG, "incrementing number of wins")
//        tttRepo.getNumWins()
//    }
}