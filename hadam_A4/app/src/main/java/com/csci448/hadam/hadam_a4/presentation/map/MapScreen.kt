package com.csci448.hadam.hadam_a4.presentation.map

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.csci448.hadam.hadam_a4.R
import com.csci448.hadam.hadam_a4.data.History
import com.csci448.hadam.hadam_a4.presentation.viewmodel.IHistoryViewModel
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
import kotlinx.coroutines.delay
import java.text.DecimalFormat
import java.time.format.DateTimeFormatter

@Composable
fun MapScreen(
    context: Context,
    historyViewModel: IHistoryViewModel,
    histories: List<History>
) {
    val showDialog = remember { mutableStateOf(false) }
    val historySnackBar = remember { mutableStateOf(histories.firstOrNull()) }

    val cameraPositionState = rememberCameraPositionState {
        position = if (histories.isEmpty()) {
            CameraPosition.fromLatLngZoom(LatLng(0.0, 0.0), 0f)
        } else {
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

    Column {
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
                        showDialog.value = true
                        historySnackBar.value = history
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
                            showDialog.value = true
                            historySnackBar.value = history
                            false
                        },
                    )
                }
            }
        }
    }
    val hSB = historySnackBar.value
    if (showDialog.value && hSB != null) {
        Log.d("MapScreen", "In snackbar")
        val current =
            hSB.dateTime.format(DateTimeFormatter.ofPattern("MM/dd/yy HH:mm"))
        Snackbar(
            modifier = Modifier
                .padding(4.dp)
                .padding(top = 550.dp),
            action = {
                TextButton(onClick = {
                    historyViewModel.deleteHistory(hSB)
                    showDialog.value = false
                }) {
                    Text(text = "Delete")
                }
            },
        ) {
            Text(
                text = "Lat/Lng: (${DecimalFormat("#.#").format(hSB.lat)}, ${
                    DecimalFormat(
                        "#.#"
                    ).format(hSB.lon)
                })  " +
                        "$current\n" +
                        "Temp: ${hSB.temp} (${hSB.description})"
            )
        }
        LaunchedEffect(Unit) {
            delay(5000)
            showDialog.value = false
        }
    }
}