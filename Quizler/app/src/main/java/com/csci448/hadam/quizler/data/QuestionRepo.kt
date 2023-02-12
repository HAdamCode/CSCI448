package com.csci448.hadam.quizler.data

import android.util.Log
import com.csci448.hadam.quizler.R

object QuestionRepo {
    private const val LOG_TAG = "448.QuestionRepo"

    init {
        Log.d(LOG_TAG, "Repo Init called")
    }

    var questions =
        listOf(
            Question(R.string.question1, false),
            Question(R.string.question2, true),
            Question(R.string.question3, true),
            Question(R.string.question4, true),
            Question(R.string.question5, false),
        )
}