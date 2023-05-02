package com.csci448.hadam.hadam_a4.presentation.about

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AboutScreen() {
    val aboutText = "This app will query the weather at your current location when you" +
            " press the Floating Action Button (FAB). Your location is plotted onto a map" +
            ". Clicking on the marker will display the time that you checked in at that location" +
            " and the weather at the time of check in. This information is represented to you in the form" +
            " of a Snackbar at the bottom of the screen.\n\nYou are also able to view the historical" +
            " weather data that you have logged in the app. Records are sorted oldest to newest by the" +
            " time that you checked in to that location.\n\nUse the hamburger menu to navigate between" +
            " pages, but if you are here then you already figured that part out."
    val scroll = rememberScrollState(0)
    Column(modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(scroll)) {
        Text(
            text = "WeathrTrackr",
            fontSize = 45.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp, top = 15.dp)
        )
        Text(
            text = "Developed By",
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 20.sp
        )
        Text(
            text = "Dope Inc.",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            fontSize = 20.sp
        )
        Text(
            text = "Version",
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 20.sp
        )
        Text(
            text = "Alpha - 0.0.1",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            fontSize = 20.sp
        )
        Text(
            text = "About",
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 20.sp
        )
        Text(
            text = aboutText,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            fontSize = 20.sp
        )
    }
}