package com.csci448.hadam.quizler.presentation.question

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

private const val LOG_TAG = "448.QuestionButton"

@Composable
fun QuestionButton(buttonText: String, onButtonClick: () -> Unit) {
    Log.d(LOG_TAG, buttonText)
    Row() {
        Button(onClick = onButtonClick) {
            Text( text = buttonText )
        }
    }
}

@Preview
@Composable
fun PreviewQuestionButton() {
    QuestionButton(buttonText = "Next") {
        
    }
}