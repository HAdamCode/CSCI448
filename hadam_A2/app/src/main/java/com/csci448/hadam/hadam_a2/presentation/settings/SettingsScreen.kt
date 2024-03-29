package com.csci448.hadam.hadam_a2.presentation.settings

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csci448.hadam.hadam_a2.presentation.viewmodel.ITTTViewModel
import com.csci448.hadam.hadam_a2.R

@Composable
fun SettingsScreen(tttViewModel: ITTTViewModel, context: Context) {
    val gameList = tttViewModel.gameListState.collectAsState().value
    Column(modifier = Modifier.fillMaxWidth()) {
        val mOnePlayerGameCheck = tttViewModel.mOnePlayerGameCheck
        val mDifficultyCheck = tttViewModel.mDifficultyCheck
        val mXGoesFirst = tttViewModel.mXGoesFirst

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.one_player_game), fontSize = 25.sp, modifier = Modifier
                    .weight(1f)
                    .padding(20.dp)
            )
            Switch(
                checked = mOnePlayerGameCheck.value,
                onCheckedChange = { mOnePlayerGameCheck.value = it },
                modifier = Modifier.padding(13.dp)
            )
        }
        if (mOnePlayerGameCheck.value) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.ai_level), fontSize = 25.sp, modifier = Modifier
                        .weight(2f)
                        .padding(20.dp)
                )
                Switch(
                    checked = mDifficultyCheck.value,
                    onCheckedChange = { mDifficultyCheck.value = it },
                    modifier = Modifier
                        .weight(1f)
                        .padding(13.dp)
                )
                if (mDifficultyCheck.value) {
                    Text(
                        text = stringResource(id = R.string.hard), fontSize = 25.sp, modifier = Modifier
                            .weight(1f)
                            .padding(20.dp)
                    )
                } else {
                    Text(
                        text = stringResource(id = R.string.easy), fontSize = 25.sp, modifier = Modifier
                            .weight(1f)
                            .padding(20.dp)
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.x_goes_first), modifier = Modifier
                    .weight(1f)
                    .padding(20.dp), fontSize = 25.sp
            )
            Switch(
                checked = mXGoesFirst.value,
                onCheckedChange = {
                    mXGoesFirst.value = it
                    tttViewModel.switchXGoesFirst()
                },
                modifier = Modifier.padding(13.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Button(
                onClick = {
                    for (game in gameList) {
                        tttViewModel.deleteGame(game)
                    }
                    Toast.makeText(context, "Game history cleared", Toast.LENGTH_SHORT).show()
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(text = stringResource(id = R.string.erase_game_history), fontSize = 18.sp)
            }
        }
    }
}
