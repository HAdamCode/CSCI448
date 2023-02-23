package com.csci448.hadam.quizler.presentation.question

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
                    onCheatAnswer: () -> Unit,
                    onCorrectAnswer: () -> Unit,
                    onWrongAnswer: () -> Unit,
                    questionStatus: QuestionStatus,
                    orientation: Int = Configuration.ORIENTATION_PORTRAIT) {
    var enabled = false
    if (questionStatus == QuestionStatus.UNANSWERED || questionStatus == QuestionStatus.CHEATED) {
        enabled = true
    }
    val correctButtonColors = ButtonDefaults.buttonColors(disabledContainerColor = Gold60, disabledContentColor = Blue20)
    val incorrectButtonColors = ButtonDefaults.buttonColors(disabledContainerColor = Red40, disabledContentColor = Gold60)
    val defaultButtonColors = ButtonDefaults.buttonColors()
    var colorToUse: ButtonColors
    Log.d(LOG_TAG, stringResource(id = question.questionTextId))
    when (orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            Row {
                QuestionTextCard(question)
                Column() {
                    colorToUse =
                        if (questionStatus == QuestionStatus.ANSWERED_CORRECT && 1 == question.correctChoiceNumber) correctButtonColors
                        else if (questionStatus == QuestionStatus.ANSWERED_INCORRECT && 1 == question.correctChoiceNumber) incorrectButtonColors
                        else defaultButtonColors
                    QuestionChoiceButton(question, questionStatus, question.choice1Id, 1, enabled, colorToUse, onCheatAnswer, onCorrectAnswer, onWrongAnswer)
                    colorToUse =
                        if (questionStatus == QuestionStatus.ANSWERED_CORRECT && 2 == question.correctChoiceNumber) correctButtonColors
                        else if (questionStatus == QuestionStatus.ANSWERED_INCORRECT && 2 == question.correctChoiceNumber) incorrectButtonColors
                        else defaultButtonColors
                    QuestionChoiceButton(question, questionStatus, question.choice2Id, 2, enabled, colorToUse, onCheatAnswer, onCorrectAnswer, onWrongAnswer)
                    if (question.choice3Id != null) {
                        colorToUse =
                            if (questionStatus == QuestionStatus.ANSWERED_CORRECT && 3 == question.correctChoiceNumber) correctButtonColors
                            else if (questionStatus == QuestionStatus.ANSWERED_INCORRECT && 3 == question.correctChoiceNumber) incorrectButtonColors
                            else defaultButtonColors
                        QuestionChoiceButton(question, questionStatus, question.choice3Id, 3, enabled, colorToUse, onCheatAnswer, onCorrectAnswer, onWrongAnswer)
                        if (question.choice4Id != null) {
                            colorToUse =
                                if (questionStatus == QuestionStatus.ANSWERED_CORRECT && 4 == question.correctChoiceNumber) correctButtonColors
                                else if (questionStatus == QuestionStatus.ANSWERED_INCORRECT && 4 == question.correctChoiceNumber) incorrectButtonColors
                                else defaultButtonColors
                            QuestionChoiceButton(question, questionStatus, question.choice4Id, 4, enabled, colorToUse, onCheatAnswer, onCorrectAnswer, onWrongAnswer)
                        }
                    }
                }
            }
        }
        else -> {
            // create portrait composable tree here
            Column {
                QuestionTextCard(question)
                Row() {
                    colorToUse =
                        if (questionStatus == QuestionStatus.ANSWERED_CORRECT && 1 == question.correctChoiceNumber) correctButtonColors
                        else if (questionStatus == QuestionStatus.ANSWERED_INCORRECT && 1 == question.correctChoiceNumber) incorrectButtonColors
                        else defaultButtonColors
                    QuestionChoiceButton(question, questionStatus, question.choice1Id, 1, enabled, colorToUse, onCheatAnswer, onCorrectAnswer, onWrongAnswer)
                    colorToUse =
                        if (questionStatus == QuestionStatus.ANSWERED_CORRECT && 2 == question.correctChoiceNumber) correctButtonColors
                        else if (questionStatus == QuestionStatus.ANSWERED_INCORRECT && 2 == question.correctChoiceNumber) incorrectButtonColors
                        else defaultButtonColors
                    QuestionChoiceButton(question, questionStatus, question.choice2Id, 2, enabled, colorToUse, onCheatAnswer, onCorrectAnswer, onWrongAnswer)
                }
                if (question.choice3Id != null) {
                    Row() {
                        colorToUse =
                            if (questionStatus == QuestionStatus.ANSWERED_CORRECT && 3 == question.correctChoiceNumber) correctButtonColors
                            else if (questionStatus == QuestionStatus.ANSWERED_INCORRECT && 3 == question.correctChoiceNumber) incorrectButtonColors
                            else defaultButtonColors
                        QuestionChoiceButton(question, questionStatus, question.choice3Id, 3, enabled, colorToUse, onCheatAnswer, onCorrectAnswer, onWrongAnswer)
                        if (question.choice4Id != null) {
                            colorToUse =
                                if (questionStatus == QuestionStatus.ANSWERED_CORRECT && 4 == question.correctChoiceNumber) correctButtonColors
                                else if (questionStatus == QuestionStatus.ANSWERED_INCORRECT && 4 == question.correctChoiceNumber) incorrectButtonColors
                                else defaultButtonColors
                            QuestionChoiceButton(question, questionStatus, question.choice4Id, 4, enabled, colorToUse, onCheatAnswer, onCorrectAnswer, onWrongAnswer)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun QuestionTextCard(question: Question) {
    ElevatedCard() {
        Text(text = stringResource(id = question.questionTextId))
    }
}

@Composable
fun QuestionChoiceButton(question: Question, questionStatus: QuestionStatus, id: Int, choice: Int, enabled: Boolean, colorToUse: ButtonColors, onCheatAnswer: () -> Unit, onCorrectAnswer: () -> Unit, onWrongAnswer: () -> Unit) {
    QuestionButton(
        buttonText = stringResource(id = id),
        enabled,
        colorToUse,
        onButtonClick =
        checkAnswerChoice(
            choice,
            question.correctChoiceNumber,
            questionStatus,
            onCheatAnswer,
            onCorrectAnswer,
            onWrongAnswer
        )
    )
}

private fun checkAnswerChoice(choiceChosen:Int,
                              correctChoice: Int,
                              questionStatus: QuestionStatus,
                              onCheatAnswer: () -> Unit,
                              onCorrectAnswer: () -> Unit,
                              onWrongAnswer: () -> Unit):
            () -> Unit{
    return if (questionStatus == QuestionStatus.CHEATED) {
        onCheatAnswer
    }
    else if (choiceChosen == correctChoice) {
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
        onCheatAnswer = { },
        onCorrectAnswer = { },
        onWrongAnswer = {},
        QuestionStatus.ANSWERED_INCORRECT)
}
@Preview(showBackground = true, device = "spec:parent=pixel_4,orientation=landscape")
@Composable
fun PreviewMultiQuestionDisplay() {
    QuestionDisplay(question = QuestionRepo.questions.last(),
        onCheatAnswer = { },
        onCorrectAnswer = { },
        onWrongAnswer = {},
        QuestionStatus.UNANSWERED)
}

@Preview
@Composable
fun PreviewDisabledQuestionDisplay() {
    QuestionDisplay(question = QuestionRepo.questions.last(),
        onCheatAnswer = { },
        onCorrectAnswer = { },
        onWrongAnswer = {},
        QuestionStatus.ANSWERED_CORRECT,
        Configuration.ORIENTATION_LANDSCAPE)
}