package com.csci448.hadam.geolocatr

import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.provider.SettingsSlicesContract.BASE_URI
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.net.toUri
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.csci448.hadam.geolocatr.ui.theme.GeoLocatrTheme
import com.google.android.gms.location.LocationSettingsStates

class MainActivity : ComponentActivity() {
    companion object {
        private const val LOG_TAG = "448.MainActivity"
        private const val ROUTE_LOCATION = "location"
        private const val ARG_LATITUDE = "lat"
        private const val ARG_LONGITUDE = "long"

        private fun formatUriString(location: Location? = null): String {
            val uriStringBuilder = StringBuilder()
            uriStringBuilder.append(BASE_URI)
            uriStringBuilder.append("/$ROUTE_LOCATION/")
            if (location == null) {
                uriStringBuilder.append("{$ARG_LATITUDE}")
            } else {
                uriStringBuilder.append(location.latitude)
            }
            uriStringBuilder.append("/")
            if (location == null) {
                uriStringBuilder.append("{$ARG_LONGITUDE}")
            } else {
                uriStringBuilder.append(location.longitude)
            }
            return uriStringBuilder.toString()
        }

        fun createPendingIntent(context: Context, location: Location):
                PendingIntent {
            val deepLinkIntent = Intent(
                Intent.ACTION_VIEW,
                formatUriString(location).toUri(),
                context,
                MainActivity::class.java
            )
            return TaskStackBuilder.create(context).run {
                addNextIntentWithParentStack(deepLinkIntent)
                getPendingIntent(
                    0,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
            }
        }
    }

    private lateinit var locationUtility: LocationUtility
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
    private lateinit var permissionLauncherNotifications: ActivityResultLauncher<String>
    private lateinit var locationLauncher: ActivityResultLauncher<IntentSenderRequest>
    private val locationAlarmReceiver = LocationAlarmReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
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
        permissionLauncherNotifications =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) {
                locationAlarmReceiver.checkPermissionAndScheduleAlarm(this@MainActivity, permissionLauncherNotifications)
            }
        setContent {
            GeoLocatrTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val locationState = locationUtility
                        .currentLocationStateFlow
                        .collectAsStateWithLifecycle(lifecycle = this@MainActivity.lifecycle)
                    val addressState = locationUtility
                        .currentAddressStateFlow
                        .collectAsStateWithLifecycle(lifecycle = this@MainActivity.lifecycle)
                    val availableState = locationUtility
                        .isLocationAvailableStateFlow
                        .collectAsStateWithLifecycle(lifecycle = this@MainActivity.lifecycle)
                    LaunchedEffect(locationState.value) {
                        locationUtility.getAddress(locationState.value)
                    }
                    val navHostController = rememberNavController()
                    NavHost(
                        navController = navHostController,
                        startDestination = ROUTE_LOCATION
                    ) {
                        composable(
                            route = ROUTE_LOCATION,
                            arguments = listOf(
                                navArgument(name = ARG_LATITUDE) {
                                    type = NavType.StringType
                                    nullable = true
                                },
                                navArgument(name = ARG_LONGITUDE) {
                                    type = NavType.StringType
                                    nullable = true
                                }
                            ),
                            deepLinks = listOf(
                                navDeepLink { uriPattern = formatUriString() }
                            )

                        ) { navBackStackEntry ->
                            navBackStackEntry.arguments?.let { args ->
                                val lat = args.getString(ARG_LATITUDE)?.toDoubleOrNull()
                                val long = args.getString(ARG_LONGITUDE)?.toDoubleOrNull()
                                if (lat != null && long != null) {
                                    val startingLocation = Location("").apply {
                                        latitude = lat
                                        longitude = long
                                    }
                                    locationUtility.setStartingLocation(startingLocation)
                                }
                            }
                            LocationScreen(
                                location = locationState.value,
                                locationAvailable = availableState.value,
                                onGetLocation = {
                                    locationUtility.checkPermissionAndGetLocation(
                                        this@MainActivity,
                                        permissionLauncher
                                    )
                                },
                                onNotify = { lastLocation ->
                                    locationAlarmReceiver.lastLocation = lastLocation
                                    locationAlarmReceiver.checkPermissionAndScheduleAlarm(
                                        this@MainActivity,
                                        permissionLauncherNotifications
                                    )
                                    Log.d(LOG_TAG, "Notify me later has been pressed")
                                },
                                address = addressState.value
                            )
                        }
                    }
                }
            }
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

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GeoLocatrTheme {
        Greeting("Android")
    }
}