package com.csci448.hadam.hadam_a3.presentation.navigation.specs

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.csci448.hadam.hadam_a3.R
import com.csci448.hadam.hadam_a3.data.Video
import com.csci448.hadam.hadam_a3.data.autocomplete.AutoComplete
import com.csci448.hadam.hadam_a3.data.autocomplete.Movies
import com.csci448.hadam.hadam_a3.presentation.newvideo.NewVideoScreen
import com.csci448.hadam.hadam_a3.presentation.viewmodel.IIMDBViewModel
import com.csci448.hadam.hadam_a3.util.NetworkConnectionUtil
import com.csci448.hadam.hadam_a3.util.api.IMDBFetchr
import com.csci448.hadam.hadam_a3.util.api.IMDBQueryFetchr
import kotlinx.coroutines.CoroutineScope
import java.lang.Exception

object NewVideoScreenSpec : IScreenSpec {
    private const val LOG_TAG = "448.NewVideoScreenSpec"

    override val route = "newVideo"
    override val arguments: List<NamedNavArgument> = emptyList()
    override val title = R.string.new_video_name
    override fun buildRoute(vararg args: String?): String = route

    @Composable
    override fun Content(
        imdbViewModel: IIMDBViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry,
        context: Context,
        coroutineScope: CoroutineScope
    ) {

        val imdbQueryFetchr = remember { IMDBQueryFetchr() }
        val apiAutoComplete = imdbQueryFetchr.AutoComplete
            .collectAsStateWithLifecycle(context = coroutineScope.coroutineContext)
        val videoState = remember {
            mutableStateOf(apiAutoComplete.value)
        }
//        val apiVideoState = imdbFetchr.titleVideo
//            .collectAsStateWithLifecycle(context = coroutineScope.coroutineContext)
        LaunchedEffect(key1 = apiAutoComplete.value) {
            val autoComplete = apiAutoComplete.value
            if (autoComplete != null) {
                videoState.value = autoComplete
            }
        }

        val searchText = imdbViewModel.currentVideoSearchState
            .collectAsStateWithLifecycle(context = coroutineScope.coroutineContext)
        val video =
            imdbViewModel.currentSearchVideoToDisplayState.collectAsStateWithLifecycle(context = coroutineScope.coroutineContext)

        NewVideoScreen(
            autoComplete = videoState.value,
            searchText = searchText.value,
            imdbViewModel = imdbViewModel,
            onSaveVideo = {
                val videoVal = video.value
                if (videoVal != null) {
                    val videoToAdd = Video(
                        id = videoVal.id,
                        name = videoVal.movie,
                        rank = videoVal.rank,
                        year = videoVal.year,
                        genre = videoVal.type,
                        actors = videoVal.starts,
                        imageUrl = if (videoVal.link != null) videoVal.link.imageUrl else "",
                        favorite = false
                    )
                    val exists = imdbViewModel.videoListState.value.contains(videoToAdd)
                    if (!exists) {
                        imdbViewModel.addVideo(videoToAdd = videoToAdd)
                    }
                }

                navController.popBackStack(
                    route = ListScreenSpec.buildRoute(),
                    inclusive = false
                )
            },
            apiButtonIsEnabled = NetworkConnectionUtil.isNetworkAvailableAndConnected(context),
            onRequestApiVideo = { imdbQueryFetchr.getVideo(searchText.value) },
            updateSearchText = { }
        )
    }

    @Composable
    override fun TopAppBarActions(
        imdbViewModel: IIMDBViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry?,
        context: Context
    ) {

    }
}