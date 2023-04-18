package com.csci448.hadam.hadam_a3.presentation.viewmodel

import com.csci448.hadam.hadam_a3.data.Video
import com.csci448.hadam.hadam_a3.data.autocomplete.Movies
import kotlinx.coroutines.flow.StateFlow

interface IIMDBViewModel {
    val videoListState: StateFlow<List<Video>>
    val currentVideoState: StateFlow<Video?>
    val currentVideoSearchState: StateFlow<String>
    val currentSearchVideoToDisplayState: StateFlow<Movies?>
    val currentFavoriteState: StateFlow<Boolean>
    fun loadVideoByUUID(uuid: String)
    fun addVideo(videoToAdd: Video)
    fun deleteVideo(videoToDelete: Video)
    fun updateSearchState(searchText: String)
    fun updateSearchVideo(movies: Movies?)
    fun toggleFavorite(id: String)
}