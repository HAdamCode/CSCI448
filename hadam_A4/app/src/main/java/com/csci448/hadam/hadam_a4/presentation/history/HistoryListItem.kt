package com.csci448.hadam.hadam_a4.presentation.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.csci448.hadam.hadam_a4.data.History

@Composable
fun HistoryListItem(
    history: History
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
//        onClick = { onVideoClick(video) }
    ) {
        Row() {
            Column(
                modifier = Modifier
                    .fillMaxWidth(.8f)
                    .padding(start = 5.dp)
            ) {
                Text(text = history.lat.toString())
                Text(text = history.lon.toString())
                Text(text = history.description)
            }
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth(.70f)
//            ) {
//                if (video.year != 0) {
//                    Text(text = video.year.toString())
//                }
//            }
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth().padding(end = 5.dp)
//            ) {
//                IconToggleButton(onCheckedChange = {
//                    onFavoriteClick()
//                }, checked = isFavorite) {
//                    Icon(
//                        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
//                        contentDescription = stringResource(R.string.app_name)
//                    )
//                }
//            }
        }
    }
}