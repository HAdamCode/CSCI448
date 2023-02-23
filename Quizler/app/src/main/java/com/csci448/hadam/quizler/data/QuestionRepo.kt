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
            Question(R.string.question1, 2),
            Question(R.string.question2, 1),
            Question(R.string.question3, 1),
            Question(R.string.question4, 1),
            Question(R.string.question5, 2),
            Question(
                R.string.question6,
                3,
                R.string.q6_choice1,
                R.string.q6_choice2,
                R.string.q6_choice3,
                R.string.q6_choice4
            ),
        )
}