package com.csci448.hadam.hadam_a4.presentation.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.csci448.hadam.hadam_a4.presentation.navigation.specs.IScreenSpec
import com.csci448.hadam.hadam_a4.presentation.viewmodel.IHistoryViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun IMDBNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    historyViewModel: IHistoryViewModel,
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
                            historyViewModel = historyViewModel,
                            context = context,
                            coroutineScope = coroutineScope
                        )
                    }
                }
            }
        }
    }
}