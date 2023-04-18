package com.csci448.hadam.hadam_a3.presentation.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.csci448.hadam.hadam_a3.presentation.navigation.specs.IScreenSpec
import com.csci448.hadam.hadam_a3.presentation.viewmodel.IIMDBViewModel
import com.csci448.hadam.hadam_a3.presentation.viewmodel.IMDBViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun IMDBNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    imdbViewModel: IIMDBViewModel,
    context: Context,
    coroutineScope: CoroutineScope
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = IScreenSpec.root
    ) {
        navigation(
            route = IScreenSpec.root,
            startDestination = IScreenSpec.startDestination
        ) {
            IScreenSpec.allScreens.forEach { (_, screen) ->
                if (screen != null) {
                    composable(
                        route = screen.route,
                        arguments = screen.arguments
                    ) { navBackStackEntry ->
                        screen.Content(
                            navController = navController,
                            navBackStackEntry = navBackStackEntry,
                            imdbViewModel = imdbViewModel,
                            context = context,
                            coroutineScope = coroutineScope
                        )
                    }
                }
            }
        }
    }
}