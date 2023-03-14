package com.csci448.hadam.hadam_a2.presentation.welcome

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.csci448.hadam.hadam_a2.R
import com.csci448.hadam.hadam_a2.presentation.navigation.specs.GameScreenSpec
import com.csci448.hadam.hadam_a2.presentation.navigation.specs.HistoryScreenSpec
import com.csci448.hadam.hadam_a2.presentation.navigation.specs.SettingsScreenSpec
import kotlin.system.exitProcess

@Composable
fun WelcomeScreen(navController: NavController) {
    when (LocalConfiguration.current.orientation) {
        Configuration.ORIENTATION_PORTRAIT -> {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                    verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(painter = painterResource(id = R.drawable.tictactoe), contentDescription = "Tic Tac Toe")
                    }
                }
                Row(modifier = Modifier) {
                    Column(modifier = Modifier) {
                        Button(onClick = {navController.navigate(GameScreenSpec.route)}, modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .size(50.dp)) {
                            Text(text = "Play game", fontSize = 20.sp)
                        }
                        Button(onClick = {navController.navigate(HistoryScreenSpec.route)}, modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .size(50.dp)) {
                            Text(text = "History", fontSize = 20.sp)
                        }
                        Button(onClick = {navController.navigate(SettingsScreenSpec.route)}, modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .size(50.dp)) {
                            Text(text = "Settings", fontSize = 20.sp)
                        }
                        Button(onClick = { exitProcess(1) }, modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .size(50.dp)) {
                            Text(text = "Quit", fontSize = 20.sp)
                        }
                    }
                }
            }
        } else -> {
        Row(modifier = Modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f).fillMaxWidth(),horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painter = painterResource(id = R.drawable.tictactoe), contentDescription = "Tic Tac Toe")
            }
            Column(modifier = Modifier.weight(1f).fillMaxWidth()) {
                Button(onClick = {navController.navigate(GameScreenSpec.route)}, modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .size(50.dp)
                    .weight(1f)) {
                    Text(text = "Play game", fontSize = 20.sp)
                }
                Button(onClick = {navController.navigate(HistoryScreenSpec.route)}, modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .size(50.dp)
                    .weight(1f)) {
                    Text(text = "History", fontSize = 20.sp)
                }
                Button(onClick = {navController.navigate(SettingsScreenSpec.route)}, modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .size(50.dp)
                    .weight(1f)) {
                    Text(text = "Settings", fontSize = 20.sp)
                }
                Button(onClick = { exitProcess(1) }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .size(50.dp)
                    .weight(1f)) {
                    Text(text = "Quit", fontSize = 20.sp)
                }
            }
        }

    }
    }

}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    val navController = rememberNavController()
    WelcomeScreen(navController)
}