package com.csci448.hadam.hadam_a3

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.csci448.hadam.hadam_a3.presentation.navigation.IMDBNavHost
import com.csci448.hadam.hadam_a3.presentation.navigation.IMDBTopBar
import com.csci448.hadam.hadam_a3.presentation.viewmodel.IIMDBViewModel
import com.csci448.hadam.hadam_a3.presentation.viewmodel.IMDBViewModelFactory
import com.csci448.hadam.hadam_a3.ui.theme.Hadam_A3Theme

class MainActivity : ComponentActivity() {
    companion object {
        private const val LOG_TAG = "448.MainActivity"
    }

    private lateinit var mIMDBViewModel: IIMDBViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(LOG_TAG, "onCreate() called")

        val factory = IMDBViewModelFactory(this)
        mIMDBViewModel = ViewModelProvider(this, factory)[factory.getViewModelClass()]
        setContent {
            MainActivityContent(imdbViewModel = mIMDBViewModel)
        }
    }
}

@Composable
private fun MainActivityContent(imdbViewModel: IIMDBViewModel) {
    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    Hadam_A3Theme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(topBar = {
                IMDBTopBar(
                    imdbViewModel = imdbViewModel,
                    navController = navController,
                    context = context
                )
            }) {
                IMDBNavHost(
                    Modifier.padding(it),
                    navController,
                    imdbViewModel,
                    context,
                    coroutineScope
                )
            }
        }
    }
}