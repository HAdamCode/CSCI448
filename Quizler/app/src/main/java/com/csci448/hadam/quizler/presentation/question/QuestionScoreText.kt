package com.csci448.hadam.quizler.presentation.question

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.csci448.hadam.quizler.R

private const val LOG_TAG = "448.QuestionScoreText"

@Composable
fun QuestionScoreText(score: Int) {
    Log.d(LOG_TAG, score.toString())
    Text(text = stringResource(id = R.string.label_score_formatter, score))
}

@Composable
@Preview
fun PreviewQuestionScoreText() {
    QuestionScoreText(0)
}