package com.csci448.hadam.hadam_a2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.PackageManagerCompat.LOG_TAG
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.csci448.hadam.hadam_a2.presentation.navigation.TTTNavHost
import com.csci448.hadam.hadam_a2.presentation.viewmodel.ITTTViewModel
import com.csci448.hadam.hadam_a2.presentation.viewmodel.TTTViewModelFactory
import com.csci448.hadam.hadam_a2.ui.theme.Hadam_A2Theme

class MainActivity : ComponentActivity() {
    companion object {
        private const val LOG_TAG = "448.MainActivity"
    }
    private lateinit var mTTTViewModel: ITTTViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(LOG_TAG, "onCreate() called")

        val factory = TTTViewModelFactory(this)
        mTTTViewModel = ViewModelProvider(this, factory)[factory.getViewModelClass()]

        setContent {
            MainActivityContent(tttViewModel = mTTTViewModel)
        }
    }
}

@Composable
private fun MainActivityContent(tttViewModel: ITTTViewModel) {
    val navController = rememberNavController()
    val context = LocalContext.current

    Hadam_A2Theme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(topBar = {

            }) {
                TTTNavHost(Modifier.padding(it), navController, tttViewModel, context)
            }
        }
    }
}