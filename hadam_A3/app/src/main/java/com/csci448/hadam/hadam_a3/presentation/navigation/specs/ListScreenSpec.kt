package com.csci448.hadam.hadam_a3.presentation.navigation.specs

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.csci448.hadam.hadam_a3.R
import com.csci448.hadam.hadam_a3.presentation.list.IMDBListScreen
import com.csci448.hadam.hadam_a3.presentation.viewmodel.IIMDBViewModel
import kotlinx.coroutines.CoroutineScope

object ListScreenSpec: IScreenSpec {
    override val route = "list"
    override val arguments: List<NamedNavArgument> = emptyList()
    override val title = R.string.app_name
    override fun buildRoute(vararg args: String?) = route

    @Composable
    override fun Content(
        imdbViewModel: IIMDBViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry,
        context: Context,
        coroutineScope: CoroutineScope
    ) {
        val videos = imdbViewModel.videoListState.collectAsState()

        IMDBListScreen(
            videoList = videos.value,
            onSelectVideo = { video ->
                navController.navigate("detail/${video.id}")
            }
        )
    }

    @Composable
    override fun TopAppBarActions(
        imdbViewModel: IIMDBViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry?,
        context: Context
    ) {
//        IconButton(onClick = { navController.navigate(route = NewVideoScreenSpec.route) }) {
//            Icon(
//                imageVector = Icons.Filled.AddCircle,
//                contentDescription = stringResource(R.string.app_name)
//            )
//        }
    }
}