package com.csci448.hadam.hadam_a2.presentation.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csci448.hadam.hadam_a2.presentation.viewmodel.ITTTViewModel
import com.csci448.hadam.hadam_a2.presentation.viewmodel.TTTViewModel

@Composable
fun SettingsScreen(tttViewModel: ITTTViewModel) {
    val gameList = tttViewModel.gameListState.collectAsState().value
    Column(modifier = Modifier.fillMaxWidth()) {
        val mNumPlayerGameCheck = tttViewModel.mNumPlayerGameCheck
        val mDifficultyCheck = tttViewModel.mDifficultyCheck
        val mXGoesFirst = tttViewModel.mXGoesFirst

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "One Player Game", fontSize = 25.sp, modifier = Modifier
                    .weight(1f)
                    .padding(20.dp)
            )
            Switch(
                checked = mNumPlayerGameCheck.value,
                onCheckedChange = { mNumPlayerGameCheck.value = it },
                modifier = Modifier.padding(13.dp)
            )
        }
        if (mNumPlayerGameCheck.value) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "AI Level", fontSize = 25.sp, modifier = Modifier
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
                        text = "Hard", fontSize = 25.sp, modifier = Modifier
                            .weight(1f)
                            .padding(20.dp)
                    )
                } else {
                    Text(
                        text = "Easy", fontSize = 25.sp, modifier = Modifier
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
                text = "X Goes First", modifier = Modifier
                    .weight(1f)
                    .padding(20.dp), fontSize = 25.sp
            )
            Switch(
                checked = mXGoesFirst.value,
                onCheckedChange = { mXGoesFirst.value = it },
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
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(text = "Erase Game History", fontSize = 18.sp)
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun SettingsScreenPreview() {
//    SettingsScreen()
//}
