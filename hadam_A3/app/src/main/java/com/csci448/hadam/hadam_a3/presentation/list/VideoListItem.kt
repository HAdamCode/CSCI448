package com.csci448.hadam.hadam_a3.presentation.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.csci448.hadam.hadam_a3.R
import com.csci448.hadam.hadam_a3.data.Video
import com.csci448.hadam.hadam_a3.data.autocomplete.Movies
import com.csci448.hadam.hadam_a3.presentation.viewmodel.IIMDBViewModel
import com.csci448.hadam.hadam_a3.presentation.viewmodel.IMDBViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoListItem(
    video: Video,
    onVideoClick: (Video) -> Unit,
    onFavoriteClick: () -> Unit,
    isFavorite: Boolean
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        onClick = { onVideoClick(video) }
    ) {
        Row() {
            Column(
                modifier = Modifier
                    .fillMaxWidth(.8f)
                    .padding(start = 5.dp)
            ) {
                Text(text = video.name, fontWeight = FontWeight.Bold)
                Text(text = video.actors)
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth(.70f)
            ) {
                if (video.year != 0) {
                    Text(text = video.year.toString())
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth().padding(end = 5.dp)
            ) {
                IconToggleButton(onCheckedChange = {
                    onFavoriteClick()
                }, checked = isFavorite) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = stringResource(R.string.app_name)
                    )
                }
            }
        }
    }
}