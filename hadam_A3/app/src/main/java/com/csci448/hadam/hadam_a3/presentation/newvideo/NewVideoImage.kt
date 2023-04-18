package com.csci448.hadam.hadam_a3.presentation.newvideo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.csci448.hadam.hadam_a3.data.autocomplete.Movies

@Composable
fun NewVideoImage(movies: Movies?) {
    Card(modifier = Modifier.fillMaxHeight(.8f).fillMaxWidth()) {
        if (movies != null) {
            AsyncImage(
                model = movies.link?.imageUrl,
                contentDescription = "",
                modifier = Modifier.padding(start = 100.dp)
            )
        }
    }
}