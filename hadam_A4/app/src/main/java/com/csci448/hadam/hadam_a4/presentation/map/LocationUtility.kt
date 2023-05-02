package com.csci448.hadam.hadam_a4.presentation.map

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.location.Geocoder
import android.location.Location
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.core.app.ActivityCompat
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsStates
import com.google.android.gms.location.Priority
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import okio.IOException

private const val LOG_TAG = "448.GeoLocatr"

class LocationUtility(private val context: Context) {
    private val mCurrentLocationStateFlow: MutableStateFlow<Location?> = MutableStateFlow(null)
    val currentLocationStateFlow: StateFlow<Location?>
        get() = mCurrentLocationStateFlow.asStateFlow()

    private val mCurrentAddressStateFlow: MutableStateFlow<String> = MutableStateFlow("")

    private val mIsLocationAvailableStateFlow = MutableStateFlow(false)

    private val geocoder = Geocoder(context)

    fun checkPermissionAndGetLocation(
        activity: Activity,
        permissionLauncher: ActivityResultLauncher<Array<String>>
    ) {
        Log.d(LOG_TAG, "Check Permissions")
        if (activity.checkSelfPermission(ACCESS_FINE_LOCATION) == PERMISSION_GRANTED || activity.checkSelfPermission(
                ACCESS_COARSE_LOCATION
            ) == PERMISSION_GRANTED
        ) {
            Log.d(LOG_TAG, "Permission Granted")
            fusedLocationProviderClient
                .requestLocationUpdates(
                    locationRequest,
                    locationCallback,
                    Looper.getMainLooper()
                )
        } else {
            Log.d(LOG_TAG, "Permission Not Granted")
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    activity,
                    ACCESS_FINE_LOCATION
                ) || ActivityCompat.shouldShowRequestPermissionRationale(
                    activity,
                    ACCESS_COARSE_LOCATION
                )
            ) {
                Toast.makeText(
                    context,
                    "You should totes allow us to track you",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                permissionLauncher.launch(arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION))
            }
        }
    }

    private val locationRequest: LocationRequest =
        LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 0L)
            .setMaxUpdates(1)
            .build()

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val lastLocation = locationResult.lastLocation
            mCurrentLocationStateFlow.value = lastLocation
        }
    }

    private val fusedLocationProviderClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    fun removeLocationRequest() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }

    suspend fun getAddress(location: Location?) {
        val addressTextBuilder = StringBuilder()
        if (location != null) {
            try {
                val addresses = geocoder.getFromLocation(
                    location.latitude,
                    location.longitude,
                    1
                )
                if (addresses != null && addresses.isNotEmpty()) {
                    val address = addresses[0]
                    for (i in 0..address.maxAddressLineIndex) {
                        if (i > 0) {
                            addressTextBuilder.append("\n")
                        }
                        addressTextBuilder.append(address.getAddressLine(i))
                    }
                }
            } catch (e: IOException) {
                Log.e(LOG_TAG, "Error getting address", e)
            }
        }
        mCurrentAddressStateFlow.update { addressTextBuilder.toString() }
    }

    fun verifyLocationSettingsStates(states: LocationSettingsStates?) {
        mIsLocationAvailableStateFlow.value = states?.isLocationUsable ?: false
    }

    fun checkIfLocationCanBeRetrieved(
        activity: Activity,
        locationLauncher: ActivityResultLauncher<IntentSenderRequest>
    ) {
        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
        val client = LocationServices.getSettingsClient(activity)
        client.checkLocationSettings(builder.build()).apply {
            addOnSuccessListener { response ->
                verifyLocationSettingsStates(response.locationSettingsStates)
            }
            addOnFailureListener { exc ->
                mIsLocationAvailableStateFlow.value = false
                if (exc is ResolvableApiException) {
                    locationLauncher
                        .launch(IntentSenderRequest.Builder(exc.resolution).build())
                }
            }
        }
    }

    fun resetLocation() {
        mCurrentLocationStateFlow.value = null
    }
}