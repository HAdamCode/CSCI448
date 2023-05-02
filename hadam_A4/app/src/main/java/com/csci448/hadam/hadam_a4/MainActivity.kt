package com.csci448.hadam.hadam_a4

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.rememberNavController
import com.csci448.hadam.hadam_a4.presentation.map.LocationUtility
import com.csci448.hadam.hadam_a4.presentation.map.MapScreen
import com.csci448.hadam.hadam_a4.presentation.navigation.HistoryFAB
import com.csci448.hadam.hadam_a4.presentation.navigation.HistoryNavHost
import com.csci448.hadam.hadam_a4.presentation.navigation.HistoryTopBar
import com.csci448.hadam.hadam_a4.presentation.navigation.specs.AboutScreenSpec
import com.csci448.hadam.hadam_a4.presentation.navigation.specs.HistoryScreenSpec
import com.csci448.hadam.hadam_a4.presentation.navigation.specs.MapScreenSpec
import com.csci448.hadam.hadam_a4.presentation.navigation.specs.SettingsScreenSpec
import com.csci448.hadam.hadam_a4.presentation.viewmodel.HistoryViewModelFactory
import com.csci448.hadam.hadam_a4.presentation.viewmodel.IHistoryViewModel
import com.csci448.hadam.hadam_a4.ui.theme.Hadam_A4Theme
import com.google.android.gms.location.LocationSettingsStates
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    companion object {
        private const val LOG_TAG = "448.MainActivity"
    }

    private lateinit var locationUtility: LocationUtility
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
    private lateinit var locationLauncher: ActivityResultLauncher<IntentSenderRequest>
    private lateinit var mHistoryViewModel: IHistoryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(LOG_TAG, "onCreate() called")
        super.onCreate(savedInstanceState)

        locationUtility = LocationUtility(context = this)

        permissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
                locationUtility.checkPermissionAndGetLocation(this@MainActivity, permissionLauncher)
            }

        locationLauncher = registerForActivityResult(
            ActivityResultContracts.StartIntentSenderForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {

                result.data?.let { data ->
                    val states = LocationSettingsStates.fromIntent(data)
                    locationUtility.verifyLocationSettingsStates(states)
                }
            }
        }

        val factory = HistoryViewModelFactory(this)
        mHistoryViewModel = ViewModelProvider(this, factory)[factory.getViewModelClass()]
        setContent {
            MainActivityContent(
                historyViewModel = mHistoryViewModel,
                this@MainActivity,
                permissionLauncher,
                locationUtility
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        locationUtility.removeLocationRequest()
    }

    override fun onStart() {
        super.onStart()
        Log.d(LOG_TAG, "Started up")
        locationUtility.checkIfLocationCanBeRetrieved(this, locationLauncher)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainActivityContent(
    historyViewModel: IHistoryViewModel,
    activity: MainActivity,
    permissionLauncher: ActivityResultLauncher<Array<String>>,
    locationUtility: LocationUtility
) {
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
                    ModalDrawerSheet(modifier = Modifier.fillMaxWidth(.7f)) {
                        Text(text = "Map Menu", fontSize = 20.sp, modifier = Modifier.padding(10.dp))
                        Card(
                            onClick = {
                                navController.navigate(route = MapScreenSpec.route)
                                coroutineScope.launch { drawerState.close() }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(.05f),
                            shape = RoundedCornerShape(0.dp)
                        ) {
                            Text("Map", modifier = Modifier.padding(7.dp))
                        }
//                        Spacer(modifier = Modifier.padding(5.dp))
                        Card(
                            onClick = {
                                navController.navigate(route = HistoryScreenSpec.route)
                                coroutineScope.launch { drawerState.close() }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(.05f),
                            shape = RoundedCornerShape(0.dp)
                        ) {
                            Text("History", modifier = Modifier.padding(7.dp))
                        }
                        Card(
                            onClick = {
                                navController.navigate(route = SettingsScreenSpec.route)
                                coroutineScope.launch { drawerState.close() }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(.05f),
                            shape = RoundedCornerShape(0.dp)
                        ) {
                            Text("Settings", modifier = Modifier.padding(7.dp))
                        }
                        Card(
                            onClick = {
                                navController.navigate(route = AboutScreenSpec.route)
                                coroutineScope.launch { drawerState.close() }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(.06f),
                            shape = RoundedCornerShape(0.dp)
                        ) {
                            Text("About", modifier = Modifier.padding(7.dp))
                        }
                        // this is already a column scope, put your drawer items in here
                    }
                },
                content = {
                    // any composable can go in here, nest your Scaffold at this point
                    Scaffold(
                        topBar = {
                            HistoryTopBar(
                                historyViewModel = historyViewModel,
                                navController = navController,
                                context = context,
                                coroutineScope = coroutineScope
                            )
                        },
                        floatingActionButton = {
                            HistoryFAB(
                                historyViewModel = historyViewModel,
                                navController = navController,
                                context = context,
                                coroutineScope = coroutineScope,
                                locationUtility = locationUtility,
                                activity = activity,
                                permissionLauncher = permissionLauncher
                            )
                            Log.d(
                                "FAB route",
                                navController.currentBackStackEntry?.destination?.route.toString()
                            )
//                            if (navController.currentDestination?.route == MapScreenSpec.route) {
//                                FloatingActionButton(
//                                    // on below line we are adding on click for our fab
//                                    onClick = {
//                                        locationUtility.checkPermissionAndGetLocation(
//                                            activity,
//                                            permissionLauncher
//                                        )
//                                    },
//                                    contentColor = Color.White,
//
//                                    ) {
//                                    Icon(Icons.Filled.Add, "")
//                                }
//                            }
                        },
                        floatingActionButtonPosition = FabPosition.End,
                    ) {
                        HistoryNavHost(
                            Modifier.padding(it),
                            navController,
                            historyViewModel,
                            context,
                            coroutineScope,
                            activity,
                            permissionLauncher,
                            locationUtility
                        )
                    }
                }
            )
        }
    }
}
