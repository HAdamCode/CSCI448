package edu.mines.csci448.examples.samodelkin.presentation.navigation.specs

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import edu.mines.csci448.examples.samodelkin.R
import edu.mines.csci448.examples.samodelkin.presentation.newcharacter.NewCharacterScreen
import edu.mines.csci448.examples.samodelkin.presentation.viewmodel.SamodelkinViewModel
import edu.mines.csci448.examples.samodelkin.util.CharacterGenerator

object NewCharacterScreenSpec : IScreenSpec {
    private const val LOG_TAG = "448.NewCharacterScreenSpec"

    override val route = "newCharacter"
    override val arguments: List<NamedNavArgument> = emptyList()
    override val title = R.string.app_name
    override fun buildRoute(vararg args: String?): String = route

    @Composable
    override fun Content(
        samodelkinViewModel: SamodelkinViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry,
        context: Context
    ) {
        val characterState = remember {
            mutableStateOf(CharacterGenerator.generateRandomCharacter(context))
        }
        NewCharacterScreen(
            character = characterState.value,
            onGenerateRandomCharacter = {
                characterState.value = CharacterGenerator.generateRandomCharacter(context)
            },
            onSaveCharacter = {
                samodelkinViewModel.addCharacter(characterState.value)
                navController.popBackStack(
                    route = ListScreenSpec.buildRoute(),
                    inclusive = false
                )
            }
        )
    }

    @Composable
    override fun TopAppBarActions(
        samodelkinViewModel: SamodelkinViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry?,
        context: Context
    ) {

    }
}