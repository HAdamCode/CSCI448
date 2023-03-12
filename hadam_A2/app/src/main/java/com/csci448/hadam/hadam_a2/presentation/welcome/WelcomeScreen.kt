package com.csci448.hadam.hadam_a2.presentation.welcome

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.csci448.hadam.hadam_a2.presentation.navigation.specs.GameScreenSpec
import com.csci448.hadam.hadam_a2.presentation.navigation.specs.HistoryScreenSpec
import com.csci448.hadam.hadam_a2.presentation.navigation.specs.SettingsScreenSpec

@Composable
fun WelcomeScreen(navController: NavHostController) {
    Column() {
        Row() {
            Text(
                text = "Welcome Screen",
                fontSize = 40.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(34.dp, 82.dp, 0.dp, 12.dp)
            )
        }
        Button(
            onClick = { navController.navigate(route = GameScreenSpec.route) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 80.dp)
        ) {
            Text(text = "Play Game")
        }
        Button(
            onClick = { navController.navigate(route = HistoryScreenSpec.route) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text(text = "History")
        }
        Button(
            onClick = { navController.navigate(route = SettingsScreenSpec.route) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text(text = "Settings")
        }
        Button(
            onClick = {  },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text(text = "Quit")
        }
    }
}