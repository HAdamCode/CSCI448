package com.csci448.hadam.hadam_a4.presentation.navigation.specs

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.csci448.hadam.hadam_a4.MainActivity
import com.csci448.hadam.hadam_a4.R
import com.csci448.hadam.hadam_a4.data.Weather
import com.csci448.hadam.hadam_a4.presentation.map.LocationUtility
import com.csci448.hadam.hadam_a4.presentation.map.MapScreen
import com.csci448.hadam.hadam_a4.presentation.viewmodel.HistoryViewModel
import com.csci448.hadam.hadam_a4.presentation.viewmodel.IHistoryViewModel
import com.csci448.hadam.hadam_a4.util.NetworkConnectionUtil
import com.csci448.hadam.hadam_a4.util.api.WeatherQueryFetchr
import com.google.android.gms.location.LocationSettingsStates
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

object MapScreenSpec : IScreenSpec {
    override val route = "map"
    override val arguments: List<NamedNavArgument> = emptyList()
    override val title = R.string.map_name
    override fun buildRoute(vararg args: String?) = route

    @Composable
    override fun Content(
        historyViewModel: IHistoryViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry,
        context: Context,
        coroutineScope: CoroutineScope,
        activity: MainActivity,
        permissionLauncher: ActivityResultLauncher<Array<String>>,
        locationUtility: LocationUtility
    ) {
        val histories = historyViewModel.historyListState.collectAsStateWithLifecycle(context = coroutineScope.coroutineContext)

        val weatherQueryFetchr = remember { WeatherQueryFetchr() }

        val apiWeather = weatherQueryFetchr.weather
            .collectAsStateWithLifecycle(context = coroutineScope.coroutineContext)
        val weatherState = remember {
            mutableStateOf(apiWeather.value)
        }

        val locationState = locationUtility
            .currentLocationStateFlow
            .collectAsStateWithLifecycle(context = coroutineScope.coroutineContext)
        val addressState = locationUtility
            .currentAddressStateFlow
            .collectAsStateWithLifecycle(context = coroutineScope.coroutineContext)
        val availableState = locationUtility
            .isLocationAvailableStateFlow
            .collectAsStateWithLifecycle(context = coroutineScope.coroutineContext)

        LaunchedEffect(locationState.value, apiWeather.value) {
            locationUtility.getAddress(locationState.value)
            val weatherVal = apiWeather.value
            if (weatherVal != null) {
                weatherState.value = weatherVal
            }
            locationUtility.resetLocation()
        }

        MapScreen(
            location = locationState.value,
            locationAvailable = availableState.value && NetworkConnectionUtil.isNetworkAvailableAndConnected(
                context
            ),
//            onGetLocation = {
//                locationUtility.checkPermissionAndGetLocation(
//                    activity,
//                    permissionLauncher
//                )
//                val loc = locationState.value
//                if (loc != null) {
//                    weatherQueryFetchr.getWeather(
//                        loc.latitude,
//                        loc.longitude,
//                        "ed3aa478701fd9607baee19c2d61f423"
//                    )
//                }
//            },
            address = addressState.value,
            context = context,
            apiDataClass = weatherState.value,
            weatherQueryFetchr = weatherQueryFetchr,
            historyViewModel = historyViewModel,
            histories = histories.value
        )
    }

    @Composable
    override fun TopAppBarActions(
        historyViewModel: IHistoryViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry?,
        context: Context,
        coroutineScope: CoroutineScope
    ) {
        val drawerValue = historyViewModel.currentDrawerState.collectAsStateWithLifecycle().value

        IconButton(onClick = {
            coroutineScope.launch { drawerValue.open() }
        }) {
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = stringResource(R.string.about_name)
            )
        }
    }

    @Composable
    override fun FABActions(
        historyViewModel: IHistoryViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry?,
        context: Context,
        coroutineScope: CoroutineScope,
        locationUtility: LocationUtility,
        activity: MainActivity,
        permissionLauncher: ActivityResultLauncher<Array<String>>,
        route: String
    ) {
        val enableSave = historyViewModel.saveLocationsEnabled.collectAsStateWithLifecycle(context = coroutineScope.coroutineContext).value

        val weatherQueryFetchr = remember { WeatherQueryFetchr() }

        val apiWeather = weatherQueryFetchr.weather
            .collectAsStateWithLifecycle(context = coroutineScope.coroutineContext)
        val weatherState = remember {
            mutableStateOf(apiWeather.value)
        }

        val locationState = locationUtility
            .currentLocationStateFlow
            .collectAsStateWithLifecycle(context = coroutineScope.coroutineContext)
        val addressState = locationUtility
            .currentAddressStateFlow
            .collectAsStateWithLifecycle(context = coroutineScope.coroutineContext)
        val availableState = locationUtility
            .isLocationAvailableStateFlow
            .collectAsStateWithLifecycle(context = coroutineScope.coroutineContext)

        LaunchedEffect(locationState.value, apiWeather.value) {
            locationUtility.getAddress(locationState.value)
            val location = locationState.value
            if (location != null) {
                weatherQueryFetchr.getWeather(
                    location.latitude,
                    location.longitude,
                    "ed3aa478701fd9607baee19c2d61f423",
                    context = context,
                    historyViewModel = historyViewModel,
                    enableSave = enableSave
                )
            }
//            historyViewModel.updateCurrentLocation(location)

            val weatherVal = apiWeather.value
            if (weatherVal != null) {
                weatherState.value = weatherVal
            }
        }
        FloatingActionButton(
            onClick = {
                locationUtility.checkPermissionAndGetLocation(
                    activity,
                    permissionLauncher
                )
            },
            contentColor = Color.White,
            ) {
            Icon(Icons.Filled.Add, "")
        }
    }

}