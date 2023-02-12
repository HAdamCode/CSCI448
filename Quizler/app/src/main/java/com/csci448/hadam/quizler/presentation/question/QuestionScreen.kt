package com.csci448.hadam.quizler.presentation.question

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.csci448.hadam.quizler.R
import com.csci448.hadam.quizler.data.QuestionRepo

private const val LOG_TAG = "448.QuestionScreen"

@Composable
fun QuestionScreen(vm: QuestionViewModel) {
    Log.d(LOG_TAG, "The screen is being composed")
    val currentContext = LocalContext.current
    Column() {
        QuestionScoreText(score = vm.currentScoreState.value)
        QuestionDisplay(question = vm.currentQuestionState.value,
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
                Toast.makeText(
                    currentContext,
                    R.string.message_wrong,
                    Toast.LENGTH_SHORT
                )
                    .show()
            })
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
    var vm = QuestionViewModel(QuestionRepo.questions);
    QuestionScreen(vm)
}