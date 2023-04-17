package com.csci448.hadam.hadam_a3.presentation.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.csci448.hadam.hadam_a3.data.titlevideo.TitleVideo

@Composable
fun IMDBListScreen(
    videoList: List<TitleVideo>,
    onSelectVideo: (TitleVideo) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
//        items(videoList) { video ->
//            VideoListItem(
//                character = character,
//                onSelectCharacter = onSelectCharacter
//            )
//        }
    }
}