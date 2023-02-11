package com.csci448.hadam.quizler.presentation.question

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.csci448.hadam.quizler.data.Question

@Composable
fun QuestionDisplay(question: Question) {
    Column() {
        Card {
            Text(text = question.questionTextId.toString())
        }
        Row() {
            
        }
        
    }
}