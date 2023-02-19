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
            QuestionButton(buttonText = stringResource(id = question.choice1Id), onButtonClick =
            checkAnswerChoice(1, question.correctChoiceNumber, onCorrectAnswer, onWrongAnswer))
            QuestionButton(buttonText = stringResource(id = question.choice2Id), onButtonClick =
            checkAnswerChoice(2, question.correctChoiceNumber, onCorrectAnswer, onWrongAnswer))
        }
        if (question.choice3Id != null) {
            Row() {
                QuestionButton(buttonText = stringResource(id = question.choice3Id), onButtonClick =
                checkAnswerChoice(3, question.correctChoiceNumber, onCorrectAnswer, onWrongAnswer))
                if (question.choice4Id != null) {
                    QuestionButton(buttonText = stringResource(id = question.choice4Id), onButtonClick =
                    checkAnswerChoice(4, question.correctChoiceNumber, onCorrectAnswer, onWrongAnswer))
                }
            }
        }
    }
}

private fun checkAnswerChoice(choiceChosen:Int, correctChoice: Int, onCorrectAnswer: () -> Unit, onWrongAnswer: () -> Unit): () -> Unit{
    return if (choiceChosen == correctChoice) {
        onCorrectAnswer
    }
    else {
        onWrongAnswer
    }

}
@Preview
@Composable
fun PreviewQuestionDisplay() {
    QuestionDisplay(question = QuestionRepo.questions.first(), onCorrectAnswer = { }) {}
}

@Preview
@Composable
fun PreviewMultiQuestionDisplay() {
    QuestionDisplay(question = QuestionRepo.questions.last(), onCorrectAnswer = { }) {}
}