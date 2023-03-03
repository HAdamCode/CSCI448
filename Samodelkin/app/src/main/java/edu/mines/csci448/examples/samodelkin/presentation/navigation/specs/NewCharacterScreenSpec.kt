package edu.mines.csci448.examples.samodelkin.presentation.navigation.specs

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import edu.mines.csci448.examples.samodelkin.presentation.viewmodel.SamodelkinViewModel

object NewCharacterScreenSpec : IScreenSpec {
    private const val LOG_TAG = "448.NewCharacterScreenSpec"

    override val route = "newCharacter"
    override val arguments: List<NamedNavArgument> = emptyList()
    override fun buildRoute(vararg args: String?): String = route

    @Composable
    override fun Content(
        samodelkinViewModel: SamodelkinViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry,
        context: Context) {
        
    }

}