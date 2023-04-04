package com.csci448.hadam.geolocatr

import android.location.Location
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun LocationScreen(
    location: Location?,
    locationAvailable: Boolean,
    onGetLocation: () -> Unit,
    address: String
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(0.0, 0.0), 0f)
    }
    val context = LocalContext.current
    LaunchedEffect(location) {
        if (location != null) {
            // include all points that should be within the bounds of the zoom
            // convex hull
            val bounds = LatLngBounds.Builder()
                .include(LatLng(location.latitude, location.longitude)).build()
            // add padding
            val padding = context.resources
                .getDimensionPixelSize(R.dimen.map_inset_padding)
            // create a camera update to smoothly move the map view
            val cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, padding)
            // move our camera!
            cameraPositionState.animate(cameraUpdate)
        }
    }
    Column() {
        Text(text = "Latitude / Longitude")
        if (location != null) {
            Text(text = "${location.latitude} / ${location.longitude}")
        }
        Text(text = "Address")
        Text(text = address)
        Button(onClick = onGetLocation, enabled = locationAvailable) {
            Text("Get Current Location")
        }
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            if (location != null) {
                val markerState = MarkerState().apply {
                    position = LatLng(location.latitude, location.longitude)
                }
                Marker(
                    state = markerState,
                    title = address,
                    snippet = "${location.latitude} / ${location.longitude}"
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewLocationScreen() {
    val locationState = remember { mutableStateOf<Location?>(null) }
    val addressState = remember { mutableStateOf("") }
    LocationScreen(
        location = locationState.value,
        locationAvailable = true,
        onGetLocation = {
            locationState.value = Location("").apply {
                latitude = 1.35
                longitude = 103.87
            }
            addressState.value = "Singapore"
        },
        address = addressState.value
    )
}