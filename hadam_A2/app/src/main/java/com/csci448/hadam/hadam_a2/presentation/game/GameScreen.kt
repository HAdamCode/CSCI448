package com.csci448.hadam.hadam_a2.presentation.game

import android.content.Context
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csci448.hadam.hadam_a2.R
import com.csci448.hadam.hadam_a2.presentation.viewmodel.ITTTViewModel

@Composable
fun GameScreen(tttViewModel: ITTTViewModel, context: Context) {
    tttViewModel.computeGameWin()
    val mSomeoneWon = tttViewModel.mExistsWinner.value
    val turn = tttViewModel.mTurn.value
    val whoWonString: String
    val turnText = "Player ${turn + 1}"

    if (tttViewModel.mOnePlayerGameCheck.value) {
        if (tttViewModel.mWinner.value == 1) {
            whoWonString = "Player ${tttViewModel.mWinner.value} Won"
        } else if (tttViewModel.mWinner.value == 2) {
            whoWonString = stringResource(id = R.string.computer_won)
        } else {
            whoWonString = "Tie"
        }
    } else {
        if (tttViewModel.mWinner.value == 1) {
            whoWonString = "Player ${tttViewModel.mWinner.value} Won"
        } else if (tttViewModel.mWinner.value == 2) {
            whoWonString = "Player ${tttViewModel.mWinner.value} Won"
        } else {
            whoWonString = "Tie"
        }
    }

    tttViewModel.computerMove()

    when (LocalConfiguration.current.orientation) {
        Configuration.ORIENTATION_PORTRAIT -> {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (mSomeoneWon) {
                    Row() {
                        Text(
                            text = stringResource(id = R.string.game_over),
                            fontSize = 17.sp,
                            modifier = Modifier.padding(20.dp)
                        )
                    }
                } else {
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
                } else {
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
                        TicTacToeGrid(tttViewModel)
                    }
                }
                if (mSomeoneWon) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Button(onClick = {
                                tttViewModel.resetGame()
                                Toast.makeText(
                                    context,
                                    "Previous Game Saved And New Game Ready",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }) {
                                Text(text = stringResource(id = R.string.new_game))
                            }
                        }
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
                            text = stringResource(id = R.string.game_over),
                            fontSize = 14.sp,
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                } else {
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
                } else {
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
                        TicTacToeGrid(tttViewModel)
                    }
                }
                if (mSomeoneWon) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Button(onClick = { tttViewModel.resetGame() }) {
                                Text(text = stringResource(id = R.string.new_game))
                            }
                        }
                    }
                }
            }
        }
    }


}

@Composable
fun TicTacToeGrid(tttViewModel: ITTTViewModel) {
    Box(
        modifier = Modifier.border(5.dp, color = MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Row {
                Cell(tttViewModel, 0)
                Cell(tttViewModel, 1)
                Cell(tttViewModel, 2)
            }
            Row {
                Cell(tttViewModel, 3)
                Cell(tttViewModel, 4)
                Cell(tttViewModel, 5)
            }
            Row {
                Cell(tttViewModel, 6)
                Cell(tttViewModel, 7)
                Cell(tttViewModel, 8)
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
                contentDescription = stringResource(id = R.string.player_drawable),
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
                        tttViewModel.clickBox(position)
                    }
            )
        } else {
            Image(
                painter = painterResource(id = tttViewModel.cells[position].imageId!!),
                contentDescription = stringResource(id = R.string.player_drawable),
                modifier = Modifier
                    .size(screenWidth, screenHeight)
                    .padding(0.dp)
                    .border(3.dp, Color.DarkGray)
            )
        }
    }
}