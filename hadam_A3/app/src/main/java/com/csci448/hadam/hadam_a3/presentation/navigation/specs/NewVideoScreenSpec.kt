package com.csci448.hadam.hadam_a3.presentation.navigation.specs

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.csci448.hadam.hadam_a3.R
import com.csci448.hadam.hadam_a3.presentation.newvideo.NewVideoScreen
import com.csci448.hadam.hadam_a3.presentation.viewmodel.IIMDBViewModel
import com.csci448.hadam.hadam_a3.util.NetworkConnectionUtil
import com.csci448.hadam.hadam_a3.util.api.IMDBFetchr
import kotlinx.coroutines.CoroutineScope

object NewVideoScreenSpec :IScreenSpec {
    private const val LOG_TAG = "448.NewVideoScreenSpec"

    override val route = "newVideo"
    override val arguments: List<NamedNavArgument> = emptyList()
    override val title = R.string.app_name
    override fun buildRoute(vararg args: String?): String = route

    @Composable
    override fun Content(
        imdbViewModel: IIMDBViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry,
        context: Context,
        coroutineScope: CoroutineScope
    ) {

        val imdbFetchr = remember { IMDBFetchr() }
        val apiVideoState = imdbFetchr.titleVideo
            .collectAsStateWithLifecycle(context = coroutineScope.coroutineContext)
//        val characterState = remember {
//            mutableStateOf()
//        }
        LaunchedEffect(key1 = apiVideoState.value) {
            val apiVideo = apiVideoState.value
//            if (apiVideo != null) {
//                characterState.value = apiVideo
//            } else {
//                characterState.value = generateRandomCharacter()
//            }
        }
        NewVideoScreen(
            titleVideo = apiVideoState.value,
            onSaveVideo = {
                apiVideoState.value?.let { it1 -> imdbViewModel.addVideo(it1) }
                navController.popBackStack(
                    route = ListScreenSpec.buildRoute(),
                    inclusive = false
                )
            },
            apiButtonIsEnabled = NetworkConnectionUtil.isNetworkAvailableAndConnected(context),
            onRequestApiCharacter = { imdbFetchr.getTitleVideo() }
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