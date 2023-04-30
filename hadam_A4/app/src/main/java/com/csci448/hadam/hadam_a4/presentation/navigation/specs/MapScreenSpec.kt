package com.csci448.hadam.hadam_a4.presentation.navigation.specs

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.csci448.hadam.hadam_a4.R
import com.csci448.hadam.hadam_a4.presentation.viewmodel.HistoryViewModel
import com.csci448.hadam.hadam_a4.presentation.viewmodel.IHistoryViewModel
import kotlinx.coroutines.CoroutineScope

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
//        val videos = historyViewModel.videoListState.collectAsState()
//        historyViewModel.resetSearch()
//        IMDBListScreen(
//            videoList = videos.value,
//            onSelectVideo = { id ->
//                navController.navigate("detail/${id}")
//            },
//            imdbViewModel = imdbViewModel
//        )
    }

//    @Composable
//    override fun TopAppBarActions(
//        historyViewModel: IHistoryViewModel,
//        navController: NavHostController,
//        navBackStackEntry: NavBackStackEntry?,
//        context: Context
//    ) {
//        IconButton(onClick = { navController.navigate(route = NewVideoScreenSpec.route) }) {
//            Icon(
//                imageVector = Icons.Filled.AddCircle,
//                contentDescription = stringResource(R.string.list_name)
//            )
//        }
//    }
}