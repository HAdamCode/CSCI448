package com.csci448.hadam.hadam_a2.presentation.game

import android.content.res.Configuration
import android.graphics.drawable.GradientDrawable.Orientation
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.traceEventEnd
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csci448.hadam.hadam_a2.R
import com.csci448.hadam.hadam_a2.data.TTTCells
import com.csci448.hadam.hadam_a2.data.TTTGame
import com.csci448.hadam.hadam_a2.presentation.viewmodel.ITTTViewModel
import com.csci448.hadam.hadam_a2.presentation.viewmodel.TTTViewModel
import java.util.*

fun computeGameWin(tttViewModel: ITTTViewModel) {
    var player1 = 0
    var player2 = 0
    for (i in 0..2) {
        for (j in 3 * i..(3 * i) + 2) {
            if (tttViewModel.cells[j].imageId == null) {
                break
            } else if (tttViewModel.cells[j].imageId == R.drawable.tictactoe) {
                player1++
            } else {
                player2++
            }
        }
        if (player1 == 3) {
            tttViewModel.mExistsWinner.value = true
            tttViewModel.mWinner.value = 1
            break
        } else if (player2 == 3) {
            tttViewModel.mExistsWinner.value = true
            tttViewModel.mWinner.value = 2
            break
        }
        player1 = 0
        player2 = 0
    }
    for (i in 0..2) {
        if (tttViewModel.cells[i].imageId == null) {
            continue
        } else if (tttViewModel.cells[i].imageId == tttViewModel.cells[i + 3].imageId && tttViewModel.cells[i].imageId == tttViewModel.cells[i + 6].imageId) {
            if (tttViewModel.cells[i].imageId == R.drawable.tictactoe) {
                tttViewModel.mExistsWinner.value = true
                tttViewModel.mWinner.value = 1
                break
            } else {
                tttViewModel.mExistsWinner.value = true
                tttViewModel.mWinner.value = 2
                break
            }
        }
    }
    if (tttViewModel.cells[0].imageId != null) {
        if (tttViewModel.cells[0].imageId == tttViewModel.cells[4].imageId && tttViewModel.cells[8].imageId == tttViewModel.cells[0].imageId) {
            if (tttViewModel.cells[0].imageId == R.drawable.tictactoe) {
                tttViewModel.mExistsWinner.value = true
                tttViewModel.mWinner.value = 1
            } else {
                tttViewModel.mExistsWinner.value = true
                tttViewModel.mWinner.value = 2
            }
        }
    }
    if (tttViewModel.cells[2].imageId != null) {
        if (tttViewModel.cells[2].imageId == tttViewModel.cells[4].imageId && tttViewModel.cells[2].imageId == tttViewModel.cells[6].imageId) {
            if (tttViewModel.cells[2].imageId == R.drawable.tictactoe) {
                tttViewModel.mExistsWinner.value = true
                tttViewModel.mWinner.value = 1
            } else {
                tttViewModel.mExistsWinner.value = true
                tttViewModel.mWinner.value = 2
            }
        }
    }
    var totalCells = 0
    for (i in 0..8) {
        if (tttViewModel.cells[i].imageId != null) {
            totalCells++
        }
    }
    if (totalCells == 9 && !tttViewModel.mExistsWinner.value) {
        tttViewModel.mExistsWinner.value = true
        tttViewModel.mWinner.value = 3
    }
}

@Composable
fun GameScreen(tttViewModel: ITTTViewModel) {
    computeGameWin(tttViewModel = tttViewModel)
    val mSomeoneWon = tttViewModel.mExistsWinner.value
    val turn = tttViewModel.mTurn.value
    val whoWonString: String
    val turnText = "Player ${turn + 1}"

    if (tttViewModel.mOnePlayerGameCheck.value) {
        if (tttViewModel.mWinner.value == 1) {
            whoWonString = "Player ${tttViewModel.mWinner.value} Won"
        }
        else if (tttViewModel.mWinner.value == 2){
            whoWonString = "Computer Won"
        }
        else {
            whoWonString = "Tie"
        }
    }
    else {
        whoWonString = "Player ${tttViewModel.mWinner.value} Won"
    }

    when (LocalConfiguration.current.orientation) {
        Configuration.ORIENTATION_PORTRAIT -> {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (mSomeoneWon) {
                    Row() {
                        Text(
                            text = "Game Over!",
                            fontSize = 17.sp,
                            modifier = Modifier.padding(20.dp)
                        )
                    }
                }
                else {
                    Row() {
                        Text(
                            text = "$turnText turn",
                            fontSize = 17.sp,
                            modifier = Modifier.padding(20.dp)
                        )
                    }
                }
                if (mSomeoneWon) {
                    Row() {
                        Text(
                            text = whoWonString,
                            fontSize = 17.sp,
                            modifier = Modifier.padding(20.dp)
                        )
                    }
                }
                else {
                    Row() {
                        Text(text = "", fontSize = 15.sp, modifier = Modifier.padding(20.dp))
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TicTacToeGrid(turn, tttViewModel)
                    }
                }
                if (mSomeoneWon) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Button(onClick = { tttViewModel.resetGame() }) {
                                Text(text = "New Game")
                            }
                        }
                    }
                }
                else {
                    Row() {
                        Text(text = "")
                    }
                }
            }
        }
        else -> {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (mSomeoneWon) {
                    Row() {
                        Text(
                            text = "Game Over!",
                            fontSize = 14.sp,
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                }
                else {
                    Row() {
                        Text(
                            text = "$turn turn",
                            fontSize = 14.sp,
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                }
                if (mSomeoneWon) {
                    Row() {
                        Text(
                            text = "$whoWonString Won",
                            fontSize = 14.sp,
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                }
                else {
                    Row() {
                        Text(text = "", fontSize = 15.sp, modifier = Modifier.padding(10.dp))
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TicTacToeGrid(turn, tttViewModel)
                    }
                }
                if (mSomeoneWon) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Button(onClick = {tttViewModel.resetGame()}) {
                                Text(text = "New Game")
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
//fun GameScreenPreview() {
//    val a2ViewModel = IA2ViewModel
//    GameScreen()
//}

@Composable
fun TicTacToeGrid(turn: Int, tttViewModel: ITTTViewModel) {
    val cell = tttViewModel.cells
    Box(
        modifier = Modifier.border(5.dp, color = MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Row {
                Cell(tttViewModel, 0)
                // [0][0]
                Cell(tttViewModel, 1)
                // [0][1]
                Cell(tttViewModel, 2)
                // [0][2]
            }
            Row {
                Cell(tttViewModel, 3)
                // [1][0]
                Cell(tttViewModel, 4)
                // [1][1]
                Cell(tttViewModel, 5)
                // [1][2]
            }
            Row {
                Cell(tttViewModel, 6)
                // [2][0]
                Cell(tttViewModel, 7)
                // [2][1]
                Cell(tttViewModel, 8)
                // [2][2]
            }
        }
    }
}

@Composable
fun Cell(tttViewModel: ITTTViewModel, position: Int) {
    val configuration = LocalConfiguration.current
    val screenWidth = (configuration.screenWidthDp / 3).dp
    var screenHeight = screenWidth

    if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE)
        screenHeight = (configuration.screenHeightDp / 5).dp
    if (tttViewModel.mExistsWinner.value) {
        if (tttViewModel.cells[position].imageId == null) {
            Text(
                text = "",
                modifier = Modifier
                    .size(screenWidth, screenHeight)
                    .padding(0.dp)
                    .border(3.dp, Color.DarkGray)
            )
        } else {
            Image(
                painter = painterResource(id = tttViewModel.cells[position].imageId!!),
                contentDescription = "Player drawable",
                modifier = Modifier
                    .size(screenWidth, screenHeight)
                    .padding(0.dp)
                    .border(3.dp, Color.DarkGray)
            )
        }
    } else {
        if (tttViewModel.cells[position].imageId == null) {
            Text(
                text = "",
                modifier = Modifier
                    .size(screenWidth, screenHeight)
                    .padding(0.dp)
                    .border(3.dp, Color.DarkGray)
                    .clickable {
                        if (tttViewModel.mTurn.value == 0) {
                            tttViewModel.changeImage(R.drawable.tictactoe, position)
                            tttViewModel.mTurn.value = (tttViewModel.mTurn.value + 1) % 2
//                        tttViewModel.mTurn.value %= 2
                        } else {
                            tttViewModel.changeImage(R.drawable.ic_launcher_background, position)
                            tttViewModel.mTurn.value = (tttViewModel.mTurn.value + 1) % 2
                        }
                    }
            )
        } else {
            Image(
                painter = painterResource(id = tttViewModel.cells[position].imageId!!),
                contentDescription = "Player drawable",
                modifier = Modifier
                    .size(screenWidth, screenHeight)
                    .padding(0.dp)
                    .border(3.dp, Color.DarkGray)
            )
        }
    }
}