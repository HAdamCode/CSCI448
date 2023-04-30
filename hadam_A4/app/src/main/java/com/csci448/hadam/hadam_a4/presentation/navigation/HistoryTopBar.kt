package com.csci448.hadam.hadam_a4.presentation.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.csci448.hadam.hadam_a4.presentation.navigation.specs.IScreenSpec
import com.csci448.hadam.hadam_a4.presentation.viewmodel.IHistoryViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun HistoryTopBar(
    historyViewModel: IHistoryViewModel,
    navController: NavHostController,
    context: Context,
    coroutineScope: CoroutineScope
) {
    val navBackStackEntryState = navController.currentBackStackEntryAsState()
    IScreenSpec.TopBar(
        historyViewModel = historyViewModel,
        navController = navController,
        navBackStackEntry = navBackStackEntryState.value,
        context = context,
        coroutineScope = coroutineScope
    )
}