package com.csci448.hadam.hadam_a3.presentation.detail

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.csci448.hadam.hadam_a3.R
import com.csci448.hadam.hadam_a3.data.titlevideo.Video

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IMDBDetailListScreen(video: Video, onVideoClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(7.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
//        onClick = {onVideoClick()}
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(.7f)
            ) {
                Text(text = video.title, fontWeight = FontWeight.Bold)
                video.description?.let { Text(text = it) }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth(.5f)
            ) {
                val minutes = video.durationInSeconds % 60
                var minuteText = ""
                if (minutes in 0..9) {
                    minuteText = "0${minutes}"
                }
//                else if( minutes==0) {
//                    minuteText = "00"
//                }
                else {
                    minuteText = minutes.toString()
                }
                val hours = video.durationInSeconds / 60
                Text(text = "${hours}:${minuteText}")
            }
            Column() {
                IconButton(onClick = { onVideoClick() }) {
                    Icon(
                        imageVector = Icons.Filled.PlayArrow,
                        contentDescription = stringResource(R.string.list_name)
                    )
                }
            }
        }
    }
}