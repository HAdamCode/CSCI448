package com.csci448.hadam.hadam_a3.presentation.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.csci448.hadam.hadam_a3.presentation.navigation.specs.IScreenSpec
import com.csci448.hadam.hadam_a3.presentation.viewmodel.IIMDBViewModel

@Composable
fun IMDBTopBar(
    imdbViewModel: IIMDBViewModel,
    navController: NavHostController,
    context: Context
) {
    val navBackStackEntryState = navController.currentBackStackEntryAsState()
    IScreenSpec.TopBar(
        imdbViewModel = imdbViewModel,
        navController = navController,
        navBackStackEntry = navBackStackEntryState.value,
        context = context
    )
}