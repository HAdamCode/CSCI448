package com.csci448.hadam.hadam_a2.presentation.history

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.csci448.hadam.hadam_a2.data.TTTGame
import com.csci448.hadam.hadam_a2.presentation.viewmodel.ITTTViewModel
import com.csci448.hadam.hadam_a2.presentation.viewmodel.TTTViewModel

@Composable
fun HistoryScreen(tttViewModel: ITTTViewModel) {
    val gameList = tttViewModel.gameListState.collectAsState().value
    var player1Wins = 0
    var computerWins = 0
    var ties = 0
    for (game: TTTGame in gameList) {
        when (game.winner) {
            "Computer Won" -> {
                computerWins++
            }
            "Player 1" -> {
                player1Wins++
            }
            else -> {
                ties++
            }
        }
    }
    Column() {
        Row() {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(text = "# Player Wins: $player1Wins", modifier = Modifier.padding(8.dp))
                Text(text = "# ComputerWins: $computerWins", modifier = Modifier.padding(8.dp))
                Text(text = "# Ties: $ties", modifier = Modifier.padding(8.dp))
            }
        }
        Row() {
            val i = 0;
            LazyColumn(modifier = Modifier.border(3.dp, color = Color.Black,shape = RoundedCornerShape(1.dp))) {
                item(gameList) {
                    for (game: TTTGame in gameList) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(text = "Game: #$i")
                            }
                            Column(modifier = Modifier.weight(1f)) {
                                Text(text = game.gameType)
                            }
                            Column(modifier = Modifier.weight(1f)) {
                                Text(text = game.winner)
                            }
                        }
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun HistoryScreenPreview() {
//
//    HistoryScreen()
//}