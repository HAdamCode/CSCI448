package com.csci448.hadam.hadam_a3.presentation.viewmodel

import com.csci448.hadam.hadam_a3.data.Video
import kotlinx.coroutines.flow.StateFlow
import java.util.*

interface IIMDBViewModel {
    val videoListState: StateFlow<List<Video>>
    val currentVideoState: StateFlow<Video?>
    val currentVideoSearchState: StateFlow<String>
    fun loadVideoByUUID(uuid: UUID)
    fun addVideo(videoToAdd: Video)
    fun deleteVideo(videoToDelete: Video)

    fun updateSearchState(searchText: String)
}