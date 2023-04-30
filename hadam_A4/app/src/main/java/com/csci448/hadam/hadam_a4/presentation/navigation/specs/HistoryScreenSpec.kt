package com.csci448.hadam.hadam_a4.presentation.navigation.specs

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.csci448.hadam.hadam_a4.R
import com.csci448.hadam.hadam_a4.presentation.viewmodel.IHistoryViewModel
import kotlinx.coroutines.CoroutineScope

object HistoryScreenSpec : IScreenSpec {
    private const val LOG_TAG = "448.HistoryScreenSpec"

    private const val ROUTE_BASE = "history"
    private const val ARG_UUID_NAME = "uuid"
    override val title = R.string.history_name

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
        coroutineScope: CoroutineScope
    ) {

    }

//    @Composable
//    override fun TopAppBarActions(
//        historyViewModel: IHistoryViewModel,
//        navController: NavHostController,
//        navBackStackEntry: NavBackStackEntry?,
//        context: Context
//    ) {
//        val video = imdbViewModel.currentVideoState
//        IconButton(onClick =
//        {
//            video.value?.let { imdbViewModel.deleteVideo(it) }
//            navController.popBackStack()
//        }) {
//            Icon(
//                imageVector = Icons.Filled.Delete,
//                contentDescription = ""
//            )
//        }
//    }
}