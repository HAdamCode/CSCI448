package com.csci448.hadam.quizler

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.csci448.hadam.quizler.data.QuestionRepo
import com.csci448.hadam.quizler.presentation.question.QuestionScreen
import com.csci448.hadam.quizler.presentation.question.QuestionViewModel
import com.csci448.hadam.quizler.presentation.question.QuestionViewModelFactory
import com.csci448.hadam.quizler.ui.theme.QuizlerTheme
import kotlin.math.absoluteValue

class MainActivity : ComponentActivity() {
    companion object {
        private const val LOG_TAG = "448.MainActivity"
        private const val index = "index"
        private const val score = "score"
    }

    private lateinit var mViewModel: QuestionViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(LOG_TAG, "onCreate() called")
        val initialIndex = savedInstanceState?.getInt(index, 0) ?: 0
        val initialScore = savedInstanceState?.getInt(score, 0) ?: 0
        val factory = QuestionViewModelFactory(initialIndex, initialScore)
        mViewModel = ViewModelProvider(this, factory)[factory.getViewModelClass()]
        setContent {
            QuizlerTheme {
                QuestionScreen(vm = mViewModel)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(LOG_TAG, "onSaveInstanceState() called")
        outState.putInt(index, mViewModel.currentQuestionIndex)
        outState.putInt(score, mViewModel.currentScoreState.value)

    }
    override fun onStart() {
        super.onStart()
        Log.d(LOG_TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(LOG_TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(LOG_TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(LOG_TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LOG_TAG, "onDestroy() called")
    }

    override fun onContentChanged() {
        super.onContentChanged()
        Log.d(LOG_TAG, "onContentChanged() called")
    }

    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)
        Log.d(LOG_TAG, "onPostCreate() called")
    }

    override fun onPostResume() {
        super.onPostResume()
        Log.d(LOG_TAG, "onPostResume() called")
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Log.d(LOG_TAG, "onAttachedToWindow() called")
    }

    override fun onEnterAnimationComplete() {
        super.onEnterAnimationComplete()
        Log.d(LOG_TAG, "onEnterAnimationComplete() called")
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Log.d(LOG_TAG, "onDetachedFromWindow() called")
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    QuizlerTheme {
        QuestionScreen(vm = QuestionViewModel(QuestionRepo.questions))
    }
}