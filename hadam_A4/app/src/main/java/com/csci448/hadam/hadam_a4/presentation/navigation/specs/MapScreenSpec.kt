package com.csci448.hadam.hadam_a4.presentation.navigation.specs

import android.content.Context
import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.csci448.hadam.hadam_a4.R
import com.csci448.hadam.hadam_a4.presentation.map.MapScreen
import com.csci448.hadam.hadam_a4.presentation.viewmodel.HistoryViewModel
import com.csci448.hadam.hadam_a4.presentation.viewmodel.IHistoryViewModel
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
        coroutineScope: CoroutineScope
    ) {
//        val locationState = locationUtility
//            .currentLocationStateFlow
//            .collectAsStateWithLifecycle(lifecycle = this@MainActivity.lifecycle)
//        historyViewModel.resetSearch()
//        coroutineScope.launch {
//            drawerState.open()
//        }
        MapScreen(

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
            coroutineScope.launch { drawerValue.open() }}) {
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = stringResource(R.string.about_name)
            )
        }
    }
}