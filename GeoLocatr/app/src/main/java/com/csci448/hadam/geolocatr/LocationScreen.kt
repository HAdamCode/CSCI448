package com.csci448.hadam.geolocatr

import android.location.Location
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.csci448.hadam.geolocatr.data.DataStoreManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.*
import kotlinx.coroutines.launch

@Composable
fun LocationScreen(
    location: Location?,
    locationAvailable: Boolean,
    onGetLocation: () -> Unit,
    onNotify: (Location) -> Unit,
    address: String
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(0.0, 0.0), 0f)
    }
    val context = LocalContext.current
    val dataStoreManager = remember { DataStoreManager(context) }

    val lifecycleOwner = LocalLifecycleOwner.current

    val dataState1 = dataStoreManager
        .dataFlow1
        .collectAsStateWithLifecycle(
            initialValue = "",
            lifecycle = lifecycleOwner.lifecycle
        )

    val dataState2 = dataStoreManager
        .dataFlow2
        .collectAsStateWithLifecycle(
            initialValue = false,
            lifecycle = lifecycleOwner.lifecycle
        )

    val mapUiSettings = MapUiSettings(
        zoomControlsEnabled = dataState2.value,
        rotationGesturesEnabled = dataState2.value,
    )

    val mapType: MapType
    if (dataState1.value == "hybrid") {
        mapType = MapType.HYBRID
    } else if (dataState1.value == "satellite") {
        mapType = MapType.SATELLITE
    } else {
        mapType = MapType.NORMAL
    }

    val mapProperties = MapProperties(
        mapType = mapType
    )

    LaunchedEffect(location) {
        if (location != null) {
            val bounds = LatLngBounds.Builder()
                .include(LatLng(location.latitude, location.longitude)).build()
            val padding = context.resources
                .getDimensionPixelSize(R.dimen.map_inset_padding)
            val cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, padding)
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
        Button(onClick = {onNotify(location!!)}, enabled = location != null) {
            Text("Notify Me Later")
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            val newData = remember { mutableStateOf( dataState1.value ) }
            Button(
                onClick = {
                    newData.value = "normal"
                    lifecycleOwner.lifecycleScope.launch { dataStoreManager.setData1(newData.value) }
                          },
                modifier = Modifier
                    .width(120.dp)
                    .padding(end = 10.dp),
            ){Text(text = "Normal")}

            Button(
                onClick = {
                    newData.value = "hybrid"
                    lifecycleOwner.lifecycleScope.launch { dataStoreManager.setData1(newData.value) }
                          },
                modifier = Modifier
                    .width(120.dp)
                    .padding(end = 10.dp),
            ){Text(text = "Hybrid")}

            Button(
                onClick = {
                    newData.value = "satellite"
                    lifecycleOwner.lifecycleScope.launch { dataStoreManager.setData1(newData.value) }
                          },
                modifier = Modifier.width(120.dp),
            ){Text(text = "Satellite")}
        }

        // Setting 2 - Label and Checkbox
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Zoom and rotation", modifier = Modifier.weight(1f))
            val newData = remember { mutableStateOf( dataState2.value ) }
            Checkbox(
                checked = newData.value,
                onCheckedChange = { value ->
                    newData.value = value
                    lifecycleOwner.lifecycleScope.launch { dataStoreManager.setData2(value) }
                },
            )
        }

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            uiSettings = mapUiSettings,
            properties = mapProperties
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
        onNotify = {},
        address = addressState.value
    )
}