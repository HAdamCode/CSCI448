package com.csci448.hadam.hadam_a3.presentation.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.csci448.hadam.hadam_a3.data.Video
import com.csci448.hadam.hadam_a3.presentation.viewmodel.IIMDBViewModel
import com.csci448.hadam.hadam_a3.presentation.viewmodel.IMDBViewModel

@Composable
fun IMDBListScreen(
    videoList: List<Video>,
    onSelectVideo: (Video) -> Unit,
    imdbViewModel: IIMDBViewModel
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(videoList) { video ->
            VideoListItem(
                video = video,
                onVideoClick = {} //imdbViewModel.deleteVideo(video)
            )
        }
    }
}