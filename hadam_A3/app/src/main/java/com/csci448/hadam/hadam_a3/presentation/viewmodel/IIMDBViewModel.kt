package com.csci448.hadam.hadam_a3.presentation.viewmodel

import com.csci448.hadam.hadam_a3.data.Video
import com.csci448.hadam.hadam_a3.data.autocomplete.Movies
import kotlinx.coroutines.flow.StateFlow
import java.util.*

interface IIMDBViewModel {
    val videoListState: StateFlow<List<Video>>
    val currentVideoState: StateFlow<Video?>
    val currentVideoSearchState: StateFlow<String>
    val currentSearchVideoToDisplayState: StateFlow<Movies?>
    fun loadVideoByUUID(uuid: UUID)
    fun addVideo(videoToAdd: Video)
    fun deleteVideo(videoToDelete: Video)
    fun updateSearchState(searchText: String)
    fun updateSearchVideo(movies: Movies)
}