package com.csci448.hadam.hadam_a3.presentation.detail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import coil.compose.AsyncImage
import com.csci448.hadam.hadam_a3.data.Video
import com.csci448.hadam.hadam_a3.data.titlevideo.TitleVideo

@SuppressLint("SuspiciousIndentation")
@Composable
fun IMDBDetailScreen(
    video: Video,
    apiButtonIsEnabled: Boolean,
    onFindButtonClick: () -> Unit,
    titleVideo: TitleVideo?,
    context: Context
) {
    Column(modifier = Modifier.padding(start = 20.dp, top = 5.dp)) {
        Row() {
            if (video.imageUrl != "") {
                AsyncImage(
                    model = video.imageUrl,
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth(.5f)
                        .padding(start = 5.dp)
                )
            }
            Column(modifier = Modifier.padding(start = 20.dp)) {
                Text(text = video.name, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = video.actors)
                Spacer(modifier = Modifier.height(16.dp))
                if (video.year != 0) {
                    Row() {
                        Text(text = "Year: ", fontWeight = FontWeight.Bold)
                        Text(text = video.year.toString())
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row() {
                    Text(text = "IMDB Rank: ", fontWeight = FontWeight.Bold)
                    Text(text = video.rank.toString())
                }
                Spacer(modifier = Modifier.height(16.dp))
                if (video.genre != null) {
                    Row() {
                        Text(text = "Genre: ", fontWeight = FontWeight.Bold)
                        Text(text = video.genre.toString())
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            modifier = Modifier.padding(start = 115.dp),
            enabled = apiButtonIsEnabled,
            onClick = onFindButtonClick
        ) {
            Text(
                text = "Find Videos",
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(.95f)
                .fillMaxHeight()
        ) {
            if (titleVideo != null) {
                items(titleVideo.resource.videos) { it ->
                    IMDBDetailListScreen(video = it, onVideoClick = {
                        val url = it.id.split("/")
                        val videoUrl = "https://imdb.com/video/${url[2]}"
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl))
                        val chooser = Intent.createChooser(intent, "Open with")
                        startActivity(context, chooser, null)
                    })
                }
            }
        }
    }
}