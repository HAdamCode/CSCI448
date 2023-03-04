package edu.mines.csci448.examples.samodelkin.presentation.navigation.specs

import android.content.Context
import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.navigation.*
import edu.mines.csci448.examples.samodelkin.R
import edu.mines.csci448.examples.samodelkin.presentation.detail.SamodelkinDetailScreen
import edu.mines.csci448.examples.samodelkin.presentation.viewmodel.SamodelkinViewModel
import java.util.*

object DetailScreenSpec : IScreenSpec {
    private const val LOG_TAG = "448.DetailScreenSpec"

    private const val ROUTE_BASE = "detail"
    private const val ARG_UUID_NAME = "uuid"
    override val title = R.string.app_name

    private fun buildFullRoute(argVal: String): String {
        var fullRoute = ROUTE_BASE
        if(argVal == ARG_UUID_NAME) {
            fullRoute += "/{$argVal}"
            Log.d(LOG_TAG, "Built base route $fullRoute")
        } else {
            fullRoute += "/$argVal"
            Log.d(LOG_TAG, "Built specific route $fullRoute")
        }
        return fullRoute
    }

    override val route = buildFullRoute(ARG_UUID_NAME)

    override val arguments: List<NamedNavArgument> = listOf(
        navArgument(ARG_UUID_NAME) {
            type = NavType.StringType
        }
    )

    override fun buildRoute(vararg args: String?): String = buildFullRoute(args[0] ?: "0")

    @Composable
    override fun Content(
        samodelkinViewModel: SamodelkinViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry,
        context: Context
    ) {
        val uuid = UUID.fromString(navBackStackEntry.arguments?.getString(ARG_UUID_NAME) ?: "")

        val character = samodelkinViewModel.currentCharacterState.collectAsState(null)

        samodelkinViewModel.loadCharacterByUUID(uuid)

        character.value?.let { SamodelkinDetailScreen(character = it) }
    }

    @Composable
    override fun TopAppBarActions(
        samodelkinViewModel: SamodelkinViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry?,
        context: Context
    ) {
        val character = samodelkinViewModel.currentCharacterState

        IconButton(onClick =
        {
            character.value?.let { samodelkinViewModel.deleteCharacter(it) }
            navController.popBackStack()
        }) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = stringResource(R.string.menu_delete_character_desc)
            )
        }
    }
}