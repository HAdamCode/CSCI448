package com.csci448.hadam.hadam_a4.presentation.map

import android.location.Location
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState

@Composable
fun MapScreen(
//    location: Location?,
//    address: String
) {
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
//        cameraPositionState = cameraPositionState,
//        uiSettings = mapUiSettings,
//        properties = mapProperties,
//        onMapLoaded = { mapReadyState.value = true }
    ) {
//        if (location != null) {
//            val markerState = MarkerState().apply {
//                position = LatLng(location.latitude, location.longitude)
//            }
//            Marker(
//                state = markerState,
//                title = address,
//                snippet = "${location.latitude} / ${location.longitude}"
//            )
//        }
    }
}