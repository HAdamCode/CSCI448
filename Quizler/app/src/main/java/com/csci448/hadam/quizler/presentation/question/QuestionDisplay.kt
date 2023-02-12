package com.csci448.hadam.quizler.presentation.question

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.csci448.hadam.quizler.R
import com.csci448.hadam.quizler.data.Question
import com.csci448.hadam.quizler.data.QuestionRepo

private const val LOG_TAG = "448.QuestionDisplay"

@Composable
fun QuestionDisplay(question: Question, onCorrectAnswer: () -> Unit, onWrongAnswer: () -> Unit) {
    Log.d(LOG_TAG, stringResource(id = question.questionTextId))
    Column() {
        Card {
            Text(text = stringResource(id = question.questionTextId))
        }
        Row() {
            Button(onClick = {
                if (question.isTrue) {
                    onCorrectAnswer()
                } else {
                    onWrongAnswer()
                }
            }) {
                Text(text = stringResource(id = R.string.label_true))
            }
            Button(onClick = {
                if (question.isTrue) {
                    onWrongAnswer()
                } else {
                    onCorrectAnswer()
                }
            }) {
                Text(text = stringResource(id = R.string.label_false))
            }
        }
    }
}

@Preview
@Composable
fun PreviewQuestionDisplay() {
    QuestionDisplay(question = QuestionRepo.questions.first(), onCorrectAnswer = { }) {}
}