package com.csci448.hadam.quizler.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.csci448.hadam.quizler.presentation.navigation.specs.IScreenSpec
import com.csci448.hadam.quizler.presentation.viewmodel.QuizlerViewModel

@Composable
fun QuizlerNavHost(navController: NavHostController, quizlerViewModel: QuizlerViewModel) {
    NavHost( navController = navController, startDestination = IScreenSpec.root )
    {
        navigation(route = IScreenSpec.root, startDestination = IScreenSpec.startDestination){
            IScreenSpec.allScreens.forEach { screen ->
                if(screen != null) {
                    composable(route = screen.route){
                        screen.Content(
                            quizlerViewModel = quizlerViewModel,
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}