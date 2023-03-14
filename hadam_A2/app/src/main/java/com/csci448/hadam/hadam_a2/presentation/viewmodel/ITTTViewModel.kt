package com.csci448.hadam.hadam_a2.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.csci448.hadam.hadam_a2.data.TTTCells
import com.csci448.hadam.hadam_a2.data.TTTGame
import kotlinx.coroutines.flow.StateFlow
import java.util.*

interface ITTTViewModel {
    val gameListState: StateFlow<List<TTTGame>>
    val currentGameState: StateFlow<TTTGame?>
    val mNumPlayerGameCheck: MutableState<Boolean>
    val mDifficultyCheck: MutableState<Boolean>
    val mXGoesFirst: MutableState<Boolean>
    val mExistsWinner: MutableState<Boolean>
    val mWinner: MutableState<Int>
    val mTurn: MutableState<Int>
    val cells: MutableList<TTTCells>
//    val currentNumGamesState: StateFlow<Int?>
    fun loadGameByUUID(uuid: UUID)
    fun addGame(gameToAdd: TTTGame)
    fun deleteGame(gameToDelete: TTTGame)
    fun changeImage(imageId: Int, cellPostition: Int)

    fun resetGame()
//    fun getNumWins()
}