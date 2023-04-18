package com.csci448.hadam.hadam_a3.presentation.navigation.specs

import android.content.Context
import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.csci448.hadam.hadam_a3.R
import com.csci448.hadam.hadam_a3.presentation.detail.IMDBDetailScreen
import com.csci448.hadam.hadam_a3.presentation.viewmodel.IIMDBViewModel
import com.csci448.hadam.hadam_a3.util.NetworkConnectionUtil
import com.csci448.hadam.hadam_a3.util.api.IMDBQueryFetchr
import kotlinx.coroutines.CoroutineScope

object DetailScreenSpec : IScreenSpec {
    private const val LOG_TAG = "448.DetailScreenSpec"

    private const val ROUTE_BASE = "detail"
    private const val ARG_UUID_NAME = "uuid"
    override val title = R.string.detail_name

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
        imdbViewModel: IIMDBViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry,
        context: Context,
        coroutineScope: CoroutineScope
    ) {
        val uuid = navBackStackEntry.arguments?.getString(ARG_UUID_NAME) ?: ""

        val video = imdbViewModel.currentVideoState.collectAsState(null)

        val imdbQueryFetchr = remember { IMDBQueryFetchr() }
        val apiTitleVideo = imdbQueryFetchr.TitleVideo
            .collectAsStateWithLifecycle(context = coroutineScope.coroutineContext)
        val videoState = remember {
            mutableStateOf(apiTitleVideo.value)
        }

        LaunchedEffect(key1 = apiTitleVideo.value) {
            val titleVideo = apiTitleVideo.value
            if (titleVideo != null) {
                videoState.value = titleVideo
            }
        }

        imdbViewModel.loadVideoByUUID(uuid)

        video.value?.let {
            IMDBDetailScreen(
                video = it,
                apiButtonIsEnabled = NetworkConnectionUtil.isNetworkAvailableAndConnected(context),
                onFindButtonClick = {
                    val videoToGet = video.value
                    if (videoToGet != null) {
                        imdbQueryFetchr.getTitleVideo(videoToGet.id)
                    }
                },
                titleVideo = videoState.value,
                context = context
            )
        }
    }

    @Composable
    override fun TopAppBarActions(
        imdbViewModel: IIMDBViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry?,
        context: Context
    ) {
        val video = imdbViewModel.currentVideoState
        IconButton(onClick =
        {
            video.value?.let { imdbViewModel.deleteVideo(it) }
            navController.popBackStack()
        }) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = ""
            )
        }
    }
}