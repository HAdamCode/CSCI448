package com.csci448.hadam.hadam_a4.presentation.map

import android.content.Context
import android.location.Location
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.csci448.hadam.hadam_a4.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen(
    location: Location?,
    locationAvailable: Boolean,
    onGetLocation: () -> Unit,
    address: String,
    context: Context
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(0.0, 0.0), 0f)
    }
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
//        Text(text = "Latitude / Longitude")
//        if (location != null) {
//            Text(text = "${location.latitude} / ${location.longitude}")
//        }
//        Text(text = "Address")
//        Text(text = address)
//        Row() {
//            Button(onClick = onGetLocation, enabled = locationAvailable) {
//                Text("Get Current Location")
//            }
//        }
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
//        uiSettings = mapUiSettings,
//        properties = mapProperties,
//        onMapLoaded = { mapReadyState.value = true }
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