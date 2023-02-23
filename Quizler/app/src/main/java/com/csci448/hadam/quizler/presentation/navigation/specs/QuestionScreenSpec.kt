package com.csci448.hadam.quizler.presentation.navigation.specs

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.csci448.hadam.quizler.presentation.question.QuestionScreen
import com.csci448.hadam.quizler.presentation.viewmodel.QuizlerViewModel

object QuestionScreenSpec: IScreenSpec {
    override val route: String = "question"

    @Composable
    override fun Content(quizlerViewModel: QuizlerViewModel, navController: NavController) {
        QuestionScreen(vm = quizlerViewModel) { navController.navigate("cheat") }
    }
}
