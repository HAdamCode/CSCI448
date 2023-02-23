package com.csci448.hadam.quizler.presentation.cheat

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.csci448.hadam.quizler.R
import com.csci448.hadam.quizler.data.QuestionRepo
import com.csci448.hadam.quizler.presentation.viewmodel.QuestionStatus
import com.csci448.hadam.quizler.presentation.viewmodel.QuizlerViewModel

@Composable
fun CheatScreen(viewModel: QuizlerViewModel) {
    Column() {
        Text(text = stringResource(id = R.string.want_to_cheat))
        Button(onClick = { viewModel.cheated() }) {
            Text(text = stringResource(id = R.string.cheat))
        }
        if (viewModel.currentQuestionStatus == QuestionStatus.CHEATED) {
            val question = QuestionRepo.questions[viewModel.currentQuestionIndex]
            val correctIndex = question.correctChoiceNumber
            if (correctIndex == 1)
                Text(text = stringResource(id = question.choice1Id))
            if (correctIndex == 2)
                Text(text = stringResource(id = question.choice2Id))
            if (correctIndex == 3 && question.choice3Id != null)
                Text(text = stringResource(id = question.choice3Id))
            if (correctIndex == 4 && question.choice4Id != null)
                Text(text = stringResource(id = question.choice4Id))
        }
    }
}

@Preview
@Composable
fun PreviewCheatScreen() {
    CheatScreen(viewModel = QuizlerViewModel(QuestionRepo.questions))
}