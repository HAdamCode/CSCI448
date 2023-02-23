package com.csci448.hadam.quizler.presentation.question

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private const val LOG_TAG = "448.QuestionButton"

@Composable
fun QuestionButton(
    buttonText: String,
    enabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    onButtonClick: () -> Unit
) {
    Log.d(LOG_TAG, buttonText)
    Row() {
        ElevatedButton(
            onClick = onButtonClick,
            enabled = enabled,
            colors = colors,
            elevation = ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 2.dp,
                pressedElevation = 8.dp,
                focusedElevation = 2.dp,
                hoveredElevation = 2.dp,
                disabledElevation = 0.dp
            )
        ) {
            Text(text = buttonText)
        }
    }
}

@Preview
@Composable
fun PreviewQuestionButton() {
    QuestionButton(buttonText = "Next", onButtonClick = {})
}

@Preview
@Composable
fun PreviewDisabledQuestionButton() {
    QuestionButton(buttonText = "Next", false, onButtonClick = {})
}