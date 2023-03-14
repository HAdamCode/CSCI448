package com.csci448.hadam.hadam_a2.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csci448.hadam.hadam_a2.R
import com.csci448.hadam.hadam_a2.data.TTTCells
import com.csci448.hadam.hadam_a2.data.TTTGame
import com.csci448.hadam.hadam_a2.data.TTTRepo
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
    override val firstPersonImageId: MutableState<Int> = mutableStateOf(R.drawable.x)
    override val secondPersonImageId: MutableState<Int> = mutableStateOf(R.drawable.o)

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

    override fun switchXGoesFirst() {
        val temp = firstPersonImageId.value
        firstPersonImageId.value = secondPersonImageId.value
        secondPersonImageId.value = temp
    }

    override fun computeGameWin() {
        var player1 = 0
        var player2 = 0
        for (i in 0..2) {
            for (j in 3 * i..(3 * i) + 2) {
                if (cells[j].imageId == null) {
                    break
                } else if (cells[j].imageId == firstPersonImageId.value) {
                    player1++
                } else {
                    player2++
                }
            }
            if (player1 == 3) {
                mExistsWinner.value = true
                mWinner.value = 1
                break
            } else if (player2 == 3) {
                mExistsWinner.value = true
                mWinner.value = 2
                break
            }
            player1 = 0
            player2 = 0
        }
        for (i in 0..2) {
            if (cells[i].imageId == null) {
                continue
            } else if (cells[i].imageId == cells[i + 3].imageId && cells[i].imageId == cells[i + 6].imageId) {
                if (cells[i].imageId == firstPersonImageId.value) {
                    mExistsWinner.value = true
                    mWinner.value = 1
                    break
                } else {
                    mExistsWinner.value = true
                    mWinner.value = 2
                    break
                }
            }
        }
        if (cells[0].imageId != null) {
            if (cells[0].imageId == cells[4].imageId && cells[8].imageId == cells[0].imageId) {
                if (cells[0].imageId == firstPersonImageId.value) {
                    mExistsWinner.value = true
                    mWinner.value = 1
                } else {
                    mExistsWinner.value = true
                    mWinner.value = 2
                }
            }
        }
        if (cells[2].imageId != null) {
            if (cells[2].imageId == cells[4].imageId && cells[2].imageId == cells[6].imageId) {
                if (cells[2].imageId == firstPersonImageId.value) {
                    mExistsWinner.value = true
                    mWinner.value = 1
                } else {
                    mExistsWinner.value = true
                    mWinner.value = 2
                }
            }
        }
        var totalCells = 0
        for (i in 0..8) {
            if (cells[i].imageId != null) {
                totalCells++
            }
        }
        if (totalCells == 9 && !mExistsWinner.value) {
            mExistsWinner.value = true
            mWinner.value = 3
        }
    }

    override fun clickBox(position: Int) {
        if (mTurn.value == 0) {
            changeImage(
                firstPersonImageId.value,
                position
            )
            mTurn.value = (mTurn.value + 1) % 2
        } else {
            changeImage(
                secondPersonImageId.value,
                position
            )
            mTurn.value = (mTurn.value + 1) % 2
        }
    }

    override fun computerMove() {
        if (mOnePlayerGameCheck.value && mTurn.value == 1 && !mDifficultyCheck.value) {
            for (i in 0..8) {
                if (cells[i].imageId == null) {
                    changeImage(secondPersonImageId.value, i)
                    mTurn.value = (mTurn.value + 1) % 2
                    break
                }
            }
        } else if (mOnePlayerGameCheck.value && mTurn.value == 1 && mDifficultyCheck.value) {
            var full = 0
            for (i in 0..8) {
                if (cells[i].imageId == null) {
                    full++
                }
            }
            while (true) {
                if (full == 0) {
                    break
                }
                val i = (0..8).random()
                if (cells[i].imageId == null) {
                    changeImage(secondPersonImageId.value, i)
                    mTurn.value = (mTurn.value + 1) % 2
                    break
                }
            }
        }
    }
}