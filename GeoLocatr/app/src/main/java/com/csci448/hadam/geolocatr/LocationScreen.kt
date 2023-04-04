package com.csci448.hadam.geolocatr

import android.location.Location
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LocationScreen(location: Location?, locationAvailable: Boolean, onGetLocation: () -> Unit, address: String ) {
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