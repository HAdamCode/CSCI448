package com.csci448.hadam.hadam_a3.presentation.newvideo

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.csci448.hadam.hadam_a3.data.autocomplete.AutoComplete
import com.csci448.hadam.hadam_a3.data.autocomplete.Movies

@Composable
fun NewVideoListScreen(movies: Movies) {
    Log.d("NewVideoListScreen", movies.movie)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ) {
        Row() {
            Column(
                modifier = Modifier
                    .fillMaxWidth(.8f)
            ) {
                Text(text = movies.movie, fontWeight = FontWeight.Bold)
                Text(text = movies.starts)
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = movies.year.toString())
            }
        }
    }
}
