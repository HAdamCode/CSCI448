package com.csci448.hadam.quizler.presentation.question

import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.csci448.hadam.quizler.R
import com.csci448.hadam.quizler.data.QuestionRepo
import com.csci448.hadam.quizler.presentation.viewmodel.QuizlerViewModel

private const val LOG_TAG = "448.QuestionScreen"

@Composable
fun QuestionScreen(vm: QuizlerViewModel, onButtonClick: () -> Unit) {
    Log.d(LOG_TAG, "The screen is being composed")
    val currentContext = LocalContext.current
    val orientation = LocalConfiguration.current.orientation
    Column() {
        Row() {
            Button(onButtonClick) {
                Text(text = "Cheat!")
            }
            Column(modifier = Modifier.padding(start = 40.dp, top = 10.dp)) {
                QuestionScoreText(score = vm.currentScoreState.value)
            }
        }

        QuestionDisplay(question = vm.currentQuestionState.value,
            onCheatAnswer = {
            vm.answeredCheated()
            Toast.makeText(
                currentContext,
                "Cheaters never prosper.",
                Toast.LENGTH_SHORT
            ).show()
        },
            onCorrectAnswer = {
                vm.answeredCorrect()
                Toast.makeText(
                    currentContext,
                    R.string.message_correct,
                    Toast.LENGTH_SHORT
                )
                    .show()
            },
            onWrongAnswer = {
                vm.answeredIncorrect()
                Toast.makeText(
                    currentContext,
                    R.string.message_wrong,
                    Toast.LENGTH_SHORT
                )
                    .show()
            },
            vm.currentQuestionStatus,
            orientation)
        Row() {
            QuestionButton(buttonText = stringResource(id = R.string.label_previous)) {
                vm.moveToPreviousQuestion()
            }
            QuestionButton(buttonText = stringResource(id = R.string.label_next)) {
                vm.moveToNextQuestion()
            }
        }
    }
}

@Preview
@Composable
fun PreviewQuestionScreen() {
    var vm = QuizlerViewModel(QuestionRepo.questions);
    QuestionScreen(vm, onButtonClick = {})
}