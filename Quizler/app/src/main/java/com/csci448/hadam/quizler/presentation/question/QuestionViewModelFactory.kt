package com.csci448.hadam.quizler.presentation.question

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.csci448.hadam.quizler.data.QuestionRepo

class QuestionViewModelFactory(private var initialIndex : Int = 0, private var initialScore : Int = 0) : ViewModelProvider.NewInstanceFactory() {

    fun getViewModelClass() = QuestionViewModel::class.java

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        Log.d(LOG_TAG, "Creating $modelClass")
        if( modelClass.isAssignableFrom(getViewModelClass()) )
            return modelClass
                .getConstructor(List::class.java, Int::class.java, Int::class.java)
                .newInstance(QuestionRepo.questions, initialIndex, initialScore)
        throw IllegalArgumentException("Unknown ViewModel")
    }

    companion object {
        private const val LOG_TAG = "448.QuestionViewModelFactory"
    }

}