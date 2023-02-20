package com.csci448.hadam.quizler.presentation.question

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.csci448.hadam.quizler.R
import com.csci448.hadam.quizler.data.Question
import com.csci448.hadam.quizler.data.QuestionRepo
import com.csci448.hadam.quizler.presentation.viewmodel.QuestionStatus
import com.csci448.hadam.quizler.ui.theme.Blue20
import com.csci448.hadam.quizler.ui.theme.Gold60
import com.csci448.hadam.quizler.ui.theme.Red40

private const val LOG_TAG = "448.QuestionDisplay"


@Composable
fun QuestionDisplay(question: Question,
                    onCorrectAnswer: () -> Unit,
                    onWrongAnswer: () -> Unit,
                    questionStatus: QuestionStatus) {
    var enabled = false
    if (questionStatus == QuestionStatus.UNANSWERED) {
        enabled = true
    }
    val correctButtonColors = ButtonDefaults.buttonColors(disabledContainerColor = Gold60, disabledContentColor = Blue20)
    val incorrectButtonColors = ButtonDefaults.buttonColors(disabledContainerColor = Red40, disabledContentColor = Gold60)
    val defaultButtonColors = ButtonDefaults.buttonColors()
    var colorToUse = ButtonDefaults.buttonColors()
    Log.d(LOG_TAG, stringResource(id = question.questionTextId))
    Column() {
        ElevatedCard() {
            Text(text = stringResource(id = question.questionTextId))
        }
        Row() {
            colorToUse =
                if (questionStatus == QuestionStatus.ANSWERED_CORRECT && 1 == question.correctChoiceNumber) correctButtonColors
                else if (questionStatus == QuestionStatus.ANSWERED_INCORRECT && 1 == question.correctChoiceNumber) incorrectButtonColors
                else defaultButtonColors
            QuestionButton(buttonText = stringResource(id = question.choice1Id), enabled, colorToUse, onButtonClick =
            checkAnswerChoice(1, question.correctChoiceNumber, onCorrectAnswer, onWrongAnswer))
            colorToUse =
                if (questionStatus == QuestionStatus.ANSWERED_CORRECT && 2 == question.correctChoiceNumber) correctButtonColors
                else if (questionStatus == QuestionStatus.ANSWERED_INCORRECT && 2 == question.correctChoiceNumber) incorrectButtonColors
                else defaultButtonColors
            QuestionButton(buttonText = stringResource(id = question.choice2Id), enabled, colorToUse, onButtonClick =
            checkAnswerChoice(2, question.correctChoiceNumber, onCorrectAnswer, onWrongAnswer))
        }
        if (question.choice3Id != null) {
            Row() {
                colorToUse =
                    if (questionStatus == QuestionStatus.ANSWERED_CORRECT && 3 == question.correctChoiceNumber) correctButtonColors
                    else if (questionStatus == QuestionStatus.ANSWERED_INCORRECT && 3 == question.correctChoiceNumber) incorrectButtonColors
                    else defaultButtonColors
                QuestionButton(buttonText = stringResource(id = question.choice3Id), enabled, colorToUse, onButtonClick =
                checkAnswerChoice(3, question.correctChoiceNumber, onCorrectAnswer, onWrongAnswer))
                if (question.choice4Id != null) {
                    colorToUse =
                        if (questionStatus == QuestionStatus.ANSWERED_CORRECT && 4 == question.correctChoiceNumber) correctButtonColors
                        else if (questionStatus == QuestionStatus.ANSWERED_INCORRECT && 4 == question.correctChoiceNumber) incorrectButtonColors
                        else defaultButtonColors
                    QuestionButton(buttonText = stringResource(id = question.choice4Id), enabled, colorToUse, onButtonClick =
                    checkAnswerChoice(4, question.correctChoiceNumber, onCorrectAnswer, onWrongAnswer))
                }
            }
        }
    }
}

private fun checkAnswerChoice(choiceChosen:Int,
                              correctChoice: Int,
                              onCorrectAnswer: () -> Unit,
                              onWrongAnswer: () -> Unit):
            () -> Unit{
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
    QuestionDisplay(question = QuestionRepo.questions.first(),
        onCorrectAnswer = { },
        onWrongAnswer = {},
        QuestionStatus.ANSWERED_INCORRECT)
}

@Preview
@Composable
fun PreviewMultiQuestionDisplay() {
    QuestionDisplay(question = QuestionRepo.questions.last(),
        onCorrectAnswer = { },
        onWrongAnswer = {},
        QuestionStatus.UNANSWERED)
}

@Preview
@Composable
fun PreviewDisabledQuestionDisplay() {
    QuestionDisplay(question = QuestionRepo.questions.last(),
        onCorrectAnswer = { },
        onWrongAnswer = {},
        QuestionStatus.ANSWERED_CORRECT)
}