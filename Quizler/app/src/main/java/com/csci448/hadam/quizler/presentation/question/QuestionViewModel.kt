package com.csci448.hadam.quizler.presentation.question

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.csci448.hadam.quizler.data.Question


class QuestionViewModel(
    private val mQuestions: List<Question>,
    private var mCurrentQuestionIndex: Int = 0,
    initialScore: Int = 0
) : ViewModel() {
    companion object {
        private const val LOG_TAG = "448.QuestionViewModel"
    }

    init {
        Log.d(LOG_TAG, "ViewModel Init called")
    }

    override fun onCleared() {
        super.onCleared()
    }

    val currentQuestionState: State<Question> get() = mCurrentQuestionState
    private val mCurrentQuestionState = mutableStateOf(mQuestions[mCurrentQuestionIndex])
    val currentQuestionIndex: Int
        get() = mCurrentQuestionIndex

    private val mCurrentScoreState = mutableStateOf(initialScore)
    val currentScoreState: State<Int> get() = mCurrentScoreState


    fun moveToNextQuestion() {
        Log.d(LOG_TAG, "Next question")
        mCurrentQuestionIndex++
        if (mCurrentQuestionIndex >= mQuestions.count()) {
            mCurrentQuestionIndex = 0
        }
        mCurrentQuestionState.value = mQuestions[mCurrentQuestionIndex]
    }

    fun moveToPreviousQuestion() {
        Log.d(LOG_TAG, "Previous question")
        mCurrentQuestionIndex--
        if (mCurrentQuestionIndex < 0) {
            mCurrentQuestionIndex = 4
        }
        mCurrentQuestionState.value = mQuestions[mCurrentQuestionIndex]
    }

    fun answeredCorrect() {
        Log.d(LOG_TAG, "Answered Correct")
        mCurrentScoreState.value++
    }
}