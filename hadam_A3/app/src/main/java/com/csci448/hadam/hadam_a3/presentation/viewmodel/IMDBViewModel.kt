package com.csci448.hadam.hadam_a3.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csci448.hadam.hadam_a3.data.IMDBRepo
import com.csci448.hadam.hadam_a3.data.Video
import com.csci448.hadam.hadam_a3.data.autocomplete.Movies
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class IMDBViewModel(private val imdbRepo: IMDBRepo) : IIMDBViewModel, ViewModel() {
    companion object {
        private const val LOG_TAG = "448.IMDBViewModel"
    }

    private val mVideos: MutableStateFlow<List<Video>> =
        MutableStateFlow(emptyList())

    override val videoListState: StateFlow<List<Video>>
        get() = mVideos.asStateFlow()

    private val mCurrentVideoState: MutableStateFlow<Video?> =
        MutableStateFlow(null)

    override val currentVideoState: StateFlow<Video?>
        get() = mCurrentVideoState.asStateFlow()

    private val mCurrentVideoIdState: MutableStateFlow<String> =
        MutableStateFlow("")

    private val mCurrentVideoSearchState: MutableStateFlow<String> =
        MutableStateFlow("")

    override val currentVideoSearchState: StateFlow<String>
        get() = mCurrentVideoSearchState.asStateFlow()

    private val mCurrentSearchVideoToDisplayState: MutableStateFlow<Movies?> =
        MutableStateFlow(null)

    override val currentSearchVideoToDisplayState: StateFlow<Movies?>
        get() = mCurrentSearchVideoToDisplayState.asStateFlow()

    private val mCurrentFavoriteState: MutableStateFlow<Boolean> =
        MutableStateFlow(false)

    override val currentFavoriteState: StateFlow<Boolean>
        get() = mCurrentFavoriteState.asStateFlow()

    init {
        viewModelScope.launch {
            imdbRepo.getVideos().collect { videoList ->
                mVideos.update { videoList }
            }
        }
        viewModelScope.launch {
            mCurrentVideoIdState
                .map { uuid -> imdbRepo.getVideo(uuid) }
                .collect { video -> mCurrentVideoState.update { video } }
        }
    }

    override fun loadVideoByUUID(uuid: String) {
        Log.d(LOG_TAG, "loadVideoByUUID($uuid)")
        mCurrentVideoIdState.update { uuid }
        return
    }

    override fun addVideo(videoToAdd: Video) {
        Log.d(LOG_TAG, "adding video $videoToAdd")
        imdbRepo.addVideo(videoToAdd)
    }

    override fun deleteVideo(videoToDelete: Video) {
        Log.d(LOG_TAG, "deleting video $videoToDelete")
        imdbRepo.deleteVideo(videoToDelete)
    }

    override fun updateSearchState(searchText: String) {
        mCurrentVideoSearchState.update { searchText }
    }

    override fun updateSearchVideo(movies: Movies?) {
        mCurrentSearchVideoToDisplayState.update { movies }
    }

    override fun toggleFavorite(id: String) {
        if (!mCurrentFavoriteState.value) {
            mCurrentFavoriteState.update { true }
            imdbRepo.toggleFavorite(id, true)
        } else {
            mCurrentFavoriteState.update { false }
            imdbRepo.toggleFavorite(id, false)
        }
    }
}