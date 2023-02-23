package com.csci448.hadam.quizler.presentation.cheat

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.csci448.hadam.quizler.data.QuestionRepo
import com.csci448.hadam.quizler.presentation.viewmodel.QuizlerViewModel

@Composable
fun CheatScreen(viewModel: QuizlerViewModel) {
    Column() {
        Text(text = "Are you sure you want to cheat?")
        Button(onClick = {  }) {
            Text(text = "Cheat!")
        }
    }
}

@Preview
@Composable
fun PreviewCheatScreen() {
    CheatScreen(viewModel = QuizlerViewModel(QuestionRepo.questions))
}