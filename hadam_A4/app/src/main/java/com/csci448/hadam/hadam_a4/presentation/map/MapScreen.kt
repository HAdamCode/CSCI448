package com.csci448.hadam.hadam_a4.presentation.map

import android.content.Context
import android.location.Location
import android.util.Log
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.csci448.hadam.hadam_a4.R
import com.csci448.hadam.hadam_a4.data.ApiDataClass
import com.csci448.hadam.hadam_a4.data.History
import com.csci448.hadam.hadam_a4.presentation.viewmodel.IHistoryViewModel
import com.csci448.hadam.hadam_a4.util.api.WeatherQueryFetchr
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import java.text.DecimalFormat
import java.time.format.DateTimeFormatter

@Composable
fun MapScreen(
    location: Location?,
    locationAvailable: Boolean,
//    onGetLocation: () -> Unit,
    address: String,
    context: Context,
    apiDataClass: ApiDataClass?,
    weatherQueryFetchr: WeatherQueryFetchr,
    historyViewModel: IHistoryViewModel,
    histories: List<History>
) {
    val cameraPositionState = rememberCameraPositionState {
        if (histories.isEmpty()) {
            position =
                CameraPosition.fromLatLngZoom(LatLng(0.0, 0.0), 0f)
        } else {
            position =
                CameraPosition.fromLatLngZoom(
                    LatLng(histories.last().lat, histories.last().lon),
                    20f
                )
        }
    }
    val currentLocation = historyViewModel.currentHistoryState.collectAsStateWithLifecycle().value
    LaunchedEffect(currentLocation, histories) {

        if (currentLocation != null) {
            val bounds = LatLngBounds.Builder()
                .include(LatLng(currentLocation.lat, currentLocation.lon)).build()
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
            uiSettings = MapUiSettings(zoomControlsEnabled = false, mapToolbarEnabled = false),
            properties = MapProperties(),
        ) {
            histories.forEach { history ->
                Log.d("MapScreen", history.lat.toString())
                val markerState = MarkerState().apply {
                    position = LatLng(history.lat, history.lon)
                }
                val clicked = remember {
                    mutableStateOf(false)
                }
                Marker(
                    state = markerState,
                    title = "Lat/Lng: (${history.lat}, ${history.lon}\n",
                    onClick = {
                        val current =
                            history.dateTime.format(DateTimeFormatter.ofPattern("MM/dd/yy HH:mm"))
                        Toast.makeText(
                            context,
                            "Lat/Lng: (${DecimalFormat("#.#").format(history.lat)}, ${
                                DecimalFormat(
                                    "#.#"
                                ).format(history.lon)
                            })  " +
                                    "$current\n" +
                                    "Temp: ${history.temp} (${history.description})",
                            Toast.LENGTH_LONG
                        ).show()
                        false
                    },
                )
                if (clicked.value) {
                    val current =
                        history.dateTime.format(DateTimeFormatter.ofPattern("MM/dd/yy HH:mm"))
                    Toast.makeText(
                        context,
                        "Lat/Lng: (${DecimalFormat("#.#").format(history.lat)}, ${
                            DecimalFormat(
                                "#.#"
                            ).format(history.lon)
                        })  " +
                                "$current\n" +
                                "Temp: ${history.temp} (${history.description})",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            val notSavedHistories =
                historyViewModel.currentLocationList.collectAsStateWithLifecycle().value
            notSavedHistories.forEach { history ->
                if (history != null) {
                    val markerState = MarkerState().apply {
                        position = LatLng(history.lat, history.lon)
                    }
                    Marker(
                        state = markerState,
                        title = "Lat/Lng: (${history.lat}, ${history.lon}\n",
                        onClick = {
                            val current =
                                history.dateTime.format(DateTimeFormatter.ofPattern("MM/dd/yy HH:mm"))
                            Toast.makeText(
                                context,
                                "Lat/Lng: (${DecimalFormat("#.#").format(history.lat)}, ${
                                    DecimalFormat(
                                        "#.#"
                                    ).format(history.lon)
                                })  " +
                                        "$current\n" +
                                        "Temp: ${history.temp} (${history.description})",
                                Toast.LENGTH_LONG
                            ).show()
                            false
                        },
                    )
                }
            }
        }
    }
}