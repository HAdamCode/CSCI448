package com.csci448.hadam.hadam_a4

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.rememberNavController
import com.csci448.hadam.hadam_a4.presentation.navigation.HistoryNavHost
import com.csci448.hadam.hadam_a4.presentation.navigation.HistoryTopBar
import com.csci448.hadam.hadam_a4.presentation.navigation.specs.AboutScreenSpec
import com.csci448.hadam.hadam_a4.presentation.navigation.specs.HistoryScreenSpec
import com.csci448.hadam.hadam_a4.presentation.navigation.specs.MapScreenSpec
import com.csci448.hadam.hadam_a4.presentation.navigation.specs.SettingsScreenSpec
import com.csci448.hadam.hadam_a4.presentation.viewmodel.HistoryViewModelFactory
import com.csci448.hadam.hadam_a4.presentation.viewmodel.IHistoryViewModel
import com.csci448.hadam.hadam_a4.ui.theme.Hadam_A4Theme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    companion object {
        private const val LOG_TAG = "448.MainActivity"
    }

    private lateinit var mHistoryViewModel: IHistoryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(LOG_TAG, "onCreate() called")

        super.onCreate(savedInstanceState)
        val factory = HistoryViewModelFactory(this)
        mHistoryViewModel = ViewModelProvider(this, factory)[factory.getViewModelClass()]
        setContent {
            MainActivityContent(historyViewModel = mHistoryViewModel)
        }
    }
}

@Composable
private fun MainActivityContent(historyViewModel: IHistoryViewModel) {
    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    val drawerState = historyViewModel.currentDrawerState.collectAsStateWithLifecycle().value
//    val drawerState = rememberDrawerState(DrawerValue.Closed)

    Hadam_A4Theme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ModalNavigationDrawer(
                drawerState = drawerState,
                drawerContent = {
                    ModalDrawerSheet() {
                        Button(
                            onClick = {
                                navController.navigate(route = MapScreenSpec.route)
                                coroutineScope.launch { drawerState.close() }
                            },
                            modifier = Modifier.fillMaxWidth(.5f)
                        ) {
                            Text("Map")
                        }
                        Button(
                            onClick = {
                                navController.navigate(route = HistoryScreenSpec.route)
                                coroutineScope.launch { drawerState.close() }
                            },
                            modifier = Modifier.fillMaxWidth(.5f)
                        ) {
                            Text("History")
                        }
                        Button(
                            onClick = {
                                navController.navigate(route = SettingsScreenSpec.route)
                                coroutineScope.launch { drawerState.close() }
                            },
                            modifier = Modifier.fillMaxWidth(.5f)
                        ) {
                            Text("Settings")
                        }
                        Button(
                            onClick = {
                                navController.navigate(route = AboutScreenSpec.route)
                                coroutineScope.launch { drawerState.close() }
                            },
                            modifier = Modifier.fillMaxWidth(.5f)
                        ) {
                            Text("About")
                        }
                        // this is already a column scope, put your drawer items in here
                    }
                },
                content = {
                    // any composable can go in here, nest your Scaffold at this point
                    Scaffold(topBar = {
                        HistoryTopBar(
                            historyViewModel = historyViewModel,
                            navController = navController,
                            context = context,
                            coroutineScope = coroutineScope
                        )
                    }) {
                        HistoryNavHost(
                            Modifier.padding(it),
                            navController,
                            historyViewModel,
                            context,
                            coroutineScope
                        )
                    }
                }
            )
        }
    }
}
