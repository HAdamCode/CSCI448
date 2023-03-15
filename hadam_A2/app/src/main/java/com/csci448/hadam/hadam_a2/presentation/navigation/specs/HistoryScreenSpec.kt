package com.csci448.hadam.hadam_a2.presentation.navigation.specs

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.csci448.hadam.hadam_a2.R
import com.csci448.hadam.hadam_a2.presentation.history.HistoryScreen
import com.csci448.hadam.hadam_a2.presentation.viewmodel.ITTTViewModel

object HistoryScreenSpec : IScreenSpec {
    override val route = "history"
    override val arguments: List<NamedNavArgument> = emptyList()
    override val title = R.string.app_name
    override fun buildRoute(vararg args: String?) = route

    @Composable
    override fun Content(
        tttViewModel: ITTTViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry,
        context: Context
    ) {
        HistoryScreen(tttViewModel)
    }
}