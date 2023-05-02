package com.csci448.hadam.hadam_a4.presentation.navigation.specs

import android.content.Context
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.csci448.hadam.hadam_a4.MainActivity
import com.csci448.hadam.hadam_a4.R
import com.csci448.hadam.hadam_a4.presentation.map.LocationUtility
import com.csci448.hadam.hadam_a4.presentation.map.MapScreen
import com.csci448.hadam.hadam_a4.presentation.viewmodel.IHistoryViewModel
import com.csci448.hadam.hadam_a4.util.NetworkConnectionUtil
import com.csci448.hadam.hadam_a4.util.api.WeatherQueryFetchr
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

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
        val histories =
            historyViewModel.historyListState.collectAsStateWithLifecycle(context = coroutineScope.coroutineContext)
        val weatherQueryFetchr = remember { WeatherQueryFetchr() }
        val apiWeather = weatherQueryFetchr.weather
            .collectAsStateWithLifecycle(context = coroutineScope.coroutineContext)
        val weatherState = remember {
            mutableStateOf(apiWeather.value)
        }
        val locationState = locationUtility
            .currentLocationStateFlow
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
            context = context,
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
        val enableSave =
            historyViewModel.saveLocationsEnabled.collectAsStateWithLifecycle(context = coroutineScope.coroutineContext).value

        val weatherQueryFetchr = remember { WeatherQueryFetchr() }

        val apiWeather = weatherQueryFetchr.weather
            .collectAsStateWithLifecycle(context = coroutineScope.coroutineContext)
        val weatherState = remember {
            mutableStateOf(apiWeather.value)
        }

        val locationState = locationUtility
            .currentLocationStateFlow
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

            val weatherVal = apiWeather.value
            if (weatherVal != null) {
                weatherState.value = weatherVal
            }
        }
        if (NetworkConnectionUtil.isNetworkAvailableAndConnected(context)) {
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
}