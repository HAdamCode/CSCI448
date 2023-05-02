package com.csci448.hadam.hadam_a4.presentation.navigation.specs

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.csci448.hadam.hadam_a4.MainActivity
import com.csci448.hadam.hadam_a4.R
import com.csci448.hadam.hadam_a4.presentation.map.LocationUtility
import com.csci448.hadam.hadam_a4.presentation.viewmodel.HistoryViewModel
import com.csci448.hadam.hadam_a4.presentation.viewmodel.IHistoryViewModel
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.coroutineContext

sealed interface IScreenSpec {
    companion object {
        private const val LOG_TAG = "448.IScreenSpec"

        val allScreens = IScreenSpec::class.sealedSubclasses.associate {
            Log.d(
                LOG_TAG,
                "allScreens: mapping route \"${it.objectInstance?.route ?: ""}\" to object \"${it.objectInstance}\""
            )
            it.objectInstance?.route to it.objectInstance
        }
        const val root = "imdb"
        val startDestination = MapScreenSpec.route

        @Composable
        fun TopBar(
            historyViewModel: IHistoryViewModel,
            navController: NavHostController,
            navBackStackEntry: NavBackStackEntry?,
            context: Context,
            coroutineScope: CoroutineScope
        ) {
            val route = navBackStackEntry?.destination?.route ?: ""
            allScreens[route]?.TopAppBarContent(
                historyViewModel, navController,
                navBackStackEntry, context,
                coroutineScope
            )
        }

        @Composable
        fun FAB(
            historyViewModel: IHistoryViewModel,
            navController: NavHostController,
            navBackStackEntry: NavBackStackEntry?,
            context: Context,
            coroutineScope: CoroutineScope,
            locationUtility: LocationUtility,
            activity: MainActivity,
            permissionLauncher: ActivityResultLauncher<Array<String>>
        ) {
            val route = navBackStackEntry?.destination?.route ?: ""
            allScreens[route]?.FABContent(
                historyViewModel, navController,
                navBackStackEntry, context,
                coroutineScope,
                locationUtility,
                activity,
                permissionLauncher,
                route
            )
        }
    }

    val route: String
    val arguments: List<NamedNavArgument>

    val title: Int
    fun buildRoute(vararg args: String?): String

    @Composable
    fun Content(
        historyViewModel: IHistoryViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry,
        context: Context,
        coroutineScope: CoroutineScope,
        activity: MainActivity,
        permissionLauncher: ActivityResultLauncher<Array<String>>,
        locationUtility: LocationUtility
    )

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun TopAppBarContent(
        historyViewModel: IHistoryViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry?,
        context: Context,
        coroutineScope: CoroutineScope
    ) {
        TopAppBar(
            title = { Text(text = stringResource(id = title)) },
            navigationIcon = {
                TopAppBarActions(
                    historyViewModel,
                    navController,
                    navBackStackEntry,
                    context,
                    coroutineScope
                )
            }
        )
    }

    @Composable
    fun TopAppBarActions(
        historyViewModel: IHistoryViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry?,
        context: Context,
        coroutineScope: CoroutineScope
    )


    @Composable
    fun FABContent(
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
        if (route == "map") {
            FABActions(
                historyViewModel = historyViewModel,
                navController = navController,
                navBackStackEntry = navBackStackEntry,
                context = context,
                coroutineScope = coroutineScope,
                locationUtility = locationUtility,
                activity = activity,
                permissionLauncher = permissionLauncher,
                route = route
            )
        }
    }

    @Composable
    fun FABActions(
        historyViewModel: IHistoryViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry?,
        context: Context,
        coroutineScope: CoroutineScope,
        locationUtility: LocationUtility,
        activity: MainActivity,
        permissionLauncher: ActivityResultLauncher<Array<String>>,
        route: String
    )
}