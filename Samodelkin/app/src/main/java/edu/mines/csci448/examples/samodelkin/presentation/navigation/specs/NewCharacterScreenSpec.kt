package edu.mines.csci448.examples.samodelkin.presentation.navigation.specs

import android.content.Context
import androidx.compose.runtime.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import edu.mines.csci448.examples.samodelkin.R
import edu.mines.csci448.examples.samodelkin.presentation.newcharacter.NewCharacterScreen
import edu.mines.csci448.examples.samodelkin.presentation.viewmodel.ISamodelkinViewModel
import edu.mines.csci448.examples.samodelkin.util.CharacterGenerator
import edu.mines.csci448.examples.samodelkin.util.CharacterGenerator.generateRandomCharacter
import edu.mines.csci448.examples.samodelkin.util.NetworkConnectionUtil
import edu.mines.csci448.examples.samodelkin.util.api.SamodelkinFetchr
import kotlinx.coroutines.CoroutineScope

object NewCharacterScreenSpec : IScreenSpec {
    private const val LOG_TAG = "448.NewCharacterScreenSpec"

    override val route = "newCharacter"
    override val arguments: List<NamedNavArgument> = emptyList()
    override val title = R.string.app_name
    override fun buildRoute(vararg args: String?): String = route

    @Composable
    override fun Content(
        samodelkinViewModel: ISamodelkinViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry,
        context: Context,
        coroutineScope: CoroutineScope
    ) {
        val characterState = remember {
            mutableStateOf(generateRandomCharacter(context))
        }
        val samodelkinFetchr = remember { SamodelkinFetchr() }
        val apiCharacterState = samodelkinFetchr.characterState
            .collectAsStateWithLifecycle(context = coroutineScope.coroutineContext)
        LaunchedEffect(key1 = apiCharacterState.value) {
            val apiCharacter = apiCharacterState.value
            if (apiCharacter != null) {
                characterState.value = apiCharacter
            }
            else {
                characterState.value = generateRandomCharacter()
            }
        }
        NewCharacterScreen(
            character = characterState.value,
            onGenerateRandomCharacter = {
                characterState.value =
                    CharacterGenerator.generateRandomCharacter(context)
            },
            onSaveCharacter = {
                samodelkinViewModel.addCharacter(characterState.value)
                navController.popBackStack(
                    route = ListScreenSpec.buildRoute(),
                    inclusive = false
                )
            },
            apiButtonIsEnabled = NetworkConnectionUtil.isNetworkAvailableAndConnected(context),
            onRequestApiCharacter = { samodelkinFetchr.getCharacter() }
        )
    }

    @Composable
    override fun TopAppBarActions(
        samodelkinViewModel: ISamodelkinViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry?,
        context: Context
    ) {

    }
}