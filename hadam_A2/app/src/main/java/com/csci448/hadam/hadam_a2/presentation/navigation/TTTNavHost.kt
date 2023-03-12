package com.csci448.hadam.hadam_a2.presentation.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.csci448.hadam.hadam_a2.presentation.navigation.specs.IScreenSpec
import com.csci448.hadam.hadam_a2.presentation.viewmodel.ITTTViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation

@Composable
fun TTTNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    tttViewModel: ITTTViewModel,
    context: Context
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
                            tttViewModel = tttViewModel,
                            context = context
                        )
                    }
                }
            }
        }
    }
}