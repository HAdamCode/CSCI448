package com.csci448.hadam.hadam_a3.presentation.navigation.specs

import android.content.Context
import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.csci448.hadam.hadam_a3.presentation.viewmodel.IIMDBViewModel
import kotlinx.coroutines.CoroutineScope
import com.csci448.hadam.hadam_a3.R


sealed interface IScreenSpec {
    companion object {
        private const val LOG_TAG = "448.IScreenSpec"

        val allScreens = IScreenSpec::class.sealedSubclasses.associate {
            Log.d(
                LOG_TAG,
                "allScreens: mapping route \"${it.objectInstance?.route ?: ""}\" to object \"${it.objectInstance}\""
            )
            it.objectInstance?.route to it.objectInstance
        }
        const val root = "imdb"
        val startDestination = ListScreenSpec.route

        @Composable
        fun TopBar(
            imdbViewModel: IIMDBViewModel,
            navController: NavHostController,
            navBackStackEntry: NavBackStackEntry?,
            context: Context
        ) {
            val route = navBackStackEntry?.destination?.route ?: ""
            allScreens[route]?.TopAppBarContent(
                imdbViewModel, navController,
                navBackStackEntry, context
            )
        }
    }

    val route: String
    val arguments: List<NamedNavArgument>

    val title: Int
    fun buildRoute(vararg args: String?): String

    @Composable
    fun Content(
        imdbViewModel: IIMDBViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry,
        context: Context,
        coroutineScope: CoroutineScope
    )

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun TopAppBarContent(
        imdbViewModel: IIMDBViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry?,
        context: Context
    ) {
        TopAppBar(navigationIcon = if (navController.previousBackStackEntry != null) {
            {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.app_name)
                    )
                }
            }
        } else {
            { }
        }, title = { Text(text = stringResource(id = title)) },
            actions = {
                TopAppBarActions(
                    imdbViewModel,
                    navController,
                    navBackStackEntry,
                    context
                )
            }
        )
    }

    @Composable
    fun TopAppBarActions(
        imdbViewModel: IIMDBViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry?,
        context: Context
    )
}