package com.csci448.hadam.quizler.presentation.question

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun QuestionButton(buttonText: String, onButtonClick: () -> Unit) {
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