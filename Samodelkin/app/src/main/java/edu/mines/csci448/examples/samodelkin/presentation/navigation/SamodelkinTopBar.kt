package edu.mines.csci448.examples.samodelkin.presentation.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import edu.mines.csci448.examples.samodelkin.presentation.navigation.specs.IScreenSpec
import edu.mines.csci448.examples.samodelkin.presentation.viewmodel.ISamodelkinViewModel

@Composable
fun SamodelkinTopBar(
    samodelkinViewModel: ISamodelkinViewModel,
    navController: NavHostController,
    context: Context
) {
    val navBackStackEntryState = navController.currentBackStackEntryAsState()
    IScreenSpec.TopBar(
        samodelkinViewModel = samodelkinViewModel,
        navController = navController,
        navBackStackEntry = navBackStackEntryState.value,
        context = context
    )
}