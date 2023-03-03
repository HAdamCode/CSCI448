package edu.mines.csci448.examples.samodelkin.presentation.navigation.specs

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import edu.mines.csci448.examples.samodelkin.R
import edu.mines.csci448.examples.samodelkin.presentation.list.SamodelkinListScreen
import edu.mines.csci448.examples.samodelkin.presentation.viewmodel.SamodelkinViewModel

object ListScreenSpec : IScreenSpec {

    override val route = "list"
    override val arguments: List<NamedNavArgument> = emptyList()
    override val title = R.string.app_name
    override fun buildRoute(vararg args: String?) = route

    @Composable
    override fun Content(
        samodelkinViewModel: SamodelkinViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry,
        context: Context
    ) {
        val characters = samodelkinViewModel.characters

        SamodelkinListScreen(
            characterList = characters,
            onSelectCharacter = { character ->
                navController.navigate("detail/${character.id}")
            }
        )
    }

}