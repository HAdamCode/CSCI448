package com.csci448.hadam.hadam_a3.presentation.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.csci448.hadam.hadam_a3.data.Video
import com.csci448.hadam.hadam_a3.data.autocomplete.Movies

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoListItem(
    video: Video,
    onVideoClick: (Video) -> Unit
    ) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        onClick = {onVideoClick(video)}
    ) {
        Row() {
            Column(
                modifier = Modifier
                    .fillMaxWidth(.8f)
            ) {
                Text(text = video.name, fontWeight = FontWeight.Bold)
                Text(text = video.actors)
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = video.year.toString())
            }
        }
    }
}