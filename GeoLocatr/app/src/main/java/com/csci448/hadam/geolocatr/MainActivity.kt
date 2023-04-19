package com.csci448.hadam.geolocatr

import android.os.Bundle
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.csci448.hadam.geolocatr.ui.theme.GeoLocatrTheme
import com.google.android.gms.location.LocationSettingsStates

class MainActivity : ComponentActivity() {
    companion object {
        private const val LOG_TAG = "448.MainActivity"
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
                    LocationScreen(
                        location = locationState.value,
                        locationAvailable = availableState.value,
                        onGetLocation = {
                            locationUtility.checkPermissionAndGetLocation(
                                this@MainActivity,
                                permissionLauncher
                            )
                        },
                        onNotify = {lastLocation ->
                            locationAlarmReceiver.lastLocation = lastLocation
                            locationAlarmReceiver.checkPermissionAndScheduleAlarm(this@MainActivity, permissionLauncherNotifications)

                            Log.d(LOG_TAG, "Notify me later has been pressed")

                        },
                        address = addressState.value
                    )
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