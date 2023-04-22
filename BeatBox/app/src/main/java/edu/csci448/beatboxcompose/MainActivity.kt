package edu.csci448.beatboxcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import edu.csci448.beatboxcompose.presentation.BeatBoxScreen
import edu.csci448.beatboxcompose.ui.theme.BeatBoxComposeTheme
import edu.csci448.beatboxcompose.presentation.viewmodel.SoundViewModel
import edu.csci448.beatboxcompose.presentation.viewmodel.SoundViewModelFactory

class MainActivity : ComponentActivity() {
    companion object {
        private const val LOG_TAG = "448.MainActivity"
    }

    private lateinit var soundViewModel: SoundViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(LOG_TAG, "onCreate() called")

        val factory = SoundViewModelFactory(assets)
        soundViewModel = ViewModelProvider(this, factory)[factory.getViewModelClassname()]

        setContent {
            BeatBoxComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BeatBoxScreen(
                        sounds = soundViewModel.sounds,
                        onPlaySound = { sound -> soundViewModel.onPlaySound(sound) }
                    )
                }
            }
        }
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

    override fun finish() {
        super.finish()
        Log.d(LOG_TAG, "finish() called")
    }
}
