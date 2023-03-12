package com.csci448.hadam.hadam_a2.presentation.viewmodel

import com.csci448.hadam.hadam_a2.data.TTTGame
import kotlinx.coroutines.flow.StateFlow
import java.util.*

interface ITTTViewModel {
    val gameListState: StateFlow<List<TTTGame>>
    val currentGameState: StateFlow<TTTGame?>
//    val currentNumGamesState: StateFlow<Int?>
    fun loadGameByUUID(uuid: UUID)
    fun addGame(gameToAdd: TTTGame)
    fun deleteGame(gameToDelete: TTTGame)
//    fun getNumWins()
}