package com.csci448.hadam.quizler.data

import com.csci448.hadam.quizler.R

object QuestionRepo {
    var questions =
        listOf(
            Question(R.string.question1, false),
            Question(R.string.question2, true),
            Question(R.string.question3, true),
            Question(R.string.question4, true),
            Question(R.string.question5, false),
        )
}