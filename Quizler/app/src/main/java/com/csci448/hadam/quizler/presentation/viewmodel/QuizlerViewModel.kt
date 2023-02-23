package com.csci448.hadam.quizler.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.csci448.hadam.quizler.data.Question


class QuizlerViewModel(
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

    private val mQuestionStatus = MutableList(mQuestions.count()) {QuestionStatus.UNANSWERED}
    private val mCurrentQuestionStatus = mutableStateOf(mQuestionStatus[mCurrentQuestionIndex])
    val currentQuestionStatus: QuestionStatus
        get() = mCurrentQuestionStatus.value


    fun moveToNextQuestion() {
        Log.d(LOG_TAG, "Next question")
        mCurrentQuestionIndex++
        if (mCurrentQuestionIndex >= mQuestions.count()) {
            mCurrentQuestionIndex = 0
        }
        mCurrentQuestionState.value = mQuestions[mCurrentQuestionIndex]
        mCurrentQuestionStatus.value = mQuestionStatus[mCurrentQuestionIndex]
    }

    fun moveToPreviousQuestion() {
        Log.d(LOG_TAG, "Previous question")
        mCurrentQuestionIndex--
        if (mCurrentQuestionIndex < 0) {
            mCurrentQuestionIndex = mQuestions.count() - 1
        }
        mCurrentQuestionState.value = mQuestions[mCurrentQuestionIndex]
        mCurrentQuestionStatus.value = mQuestionStatus[mCurrentQuestionIndex]
    }

    fun answeredCorrect() {
        Log.d(LOG_TAG, "Answered Correct")
        mCurrentScoreState.value++
        mCurrentQuestionStatus.value = QuestionStatus.ANSWERED_CORRECT
        mQuestionStatus[mCurrentQuestionIndex] = mCurrentQuestionStatus.value
    }

    fun answeredIncorrect() {
        Log.d(LOG_TAG, "Answered Incorrect")
        mCurrentQuestionStatus.value = QuestionStatus.ANSWERED_INCORRECT
        mQuestionStatus[mCurrentQuestionIndex] = mCurrentQuestionStatus.value
    }

    fun cheated() {
        mCurrentQuestionStatus.value = QuestionStatus.CHEATED
    }

    fun answeredCheated() {
        mCurrentQuestionStatus.value = QuestionStatus.ANSWERED_CHEATED
    }
}