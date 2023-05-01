package com.csci448.hadam.hadam_a4.presentation.navigation

import android.content.Context
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.csci448.hadam.hadam_a4.MainActivity
import com.csci448.hadam.hadam_a4.presentation.map.LocationUtility
import com.csci448.hadam.hadam_a4.presentation.navigation.specs.IScreenSpec
import com.csci448.hadam.hadam_a4.presentation.viewmodel.IHistoryViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun HistoryFAB(
    historyViewModel: IHistoryViewModel,
    navController: NavHostController,
    context: Context,
    coroutineScope: CoroutineScope,
    locationUtility: LocationUtility,
    activity: MainActivity,
    permissionLauncher: ActivityResultLauncher<Array<String>>
) {
    val navBackStackEntryState = navController.currentBackStackEntryAsState()
    IScreenSpec.FAB(
        historyViewModel = historyViewModel,
        navController = navController,
        navBackStackEntry = navBackStackEntryState.value,
        context = context,
        coroutineScope = coroutineScope,
        locationUtility = locationUtility,
        activity = activity,
        permissionLauncher = permissionLauncher
    )
}