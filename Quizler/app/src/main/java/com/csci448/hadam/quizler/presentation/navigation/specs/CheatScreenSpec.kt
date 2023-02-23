package com.csci448.hadam.quizler.presentation.navigation.specs

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.csci448.hadam.quizler.presentation.cheat.CheatScreen
import com.csci448.hadam.quizler.presentation.question.QuestionScreen
import com.csci448.hadam.quizler.presentation.viewmodel.QuizlerViewModel

object CheatScreenSpec: IScreenSpec {
    override val route: String = "cheat"
    @Composable
    override fun Content(quizlerViewModel: QuizlerViewModel, navController: NavController) {
        CheatScreen(viewModel = quizlerViewModel)
    }
}