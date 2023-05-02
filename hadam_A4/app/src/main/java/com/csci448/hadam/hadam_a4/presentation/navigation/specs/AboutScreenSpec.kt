package com.csci448.hadam.hadam_a4.presentation.navigation.specs

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
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
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.csci448.hadam.hadam_a4.MainActivity
import com.csci448.hadam.hadam_a4.R
import com.csci448.hadam.hadam_a4.presentation.about.AboutScreen
import com.csci448.hadam.hadam_a4.presentation.map.LocationUtility
import com.csci448.hadam.hadam_a4.presentation.viewmodel.IHistoryViewModel
import com.csci448.hadam.hadam_a4.util.api.WeatherQueryFetchr
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

object AboutScreenSpec : IScreenSpec {
    private const val LOG_TAG = "448.AboutScreenSpec"

    private const val ROUTE_BASE = "about"
    private const val ARG_UUID_NAME = "uuid"
    override val title = R.string.about_name

    private fun buildFullRoute(argVal: String): String {
        var fullRoute = ROUTE_BASE
        if (argVal == ARG_UUID_NAME) {
            fullRoute += "/{$argVal}"
            Log.d(LOG_TAG, "Built base route $fullRoute")
        } else {
            fullRoute += "/$argVal"
            Log.d(LOG_TAG, "Built specific route $fullRoute")
        }
        return fullRoute
    }

    override val route = buildFullRoute(ARG_UUID_NAME)

    override val arguments: List<NamedNavArgument> = listOf(
        navArgument(ARG_UUID_NAME) {
            type = NavType.StringType
        }
    )

    override fun buildRoute(vararg args: String?): String = buildFullRoute(args[0] ?: "0")

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
        AboutScreen()
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
            coroutineScope.launch { drawerValue.open() }}) {
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
    }
}