package com.csci448.hadam.hadam_a3.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csci448.hadam.hadam_a3.data.IMDBRepo
import com.csci448.hadam.hadam_a3.data.titlevideo.TitleVideo
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*

class IMDBViewModel(private val imdbRepo: IMDBRepo): IIMDBViewModel, ViewModel() {
    companion object {
        private const val LOG_TAG = "448.IMDBViewModel"
    }

    private val mVideos: MutableStateFlow<List<TitleVideo>> =
        MutableStateFlow(emptyList())

    override val videoListState: StateFlow<List<TitleVideo>>
        get() = mVideos.asStateFlow()

    private val mCurrentVideoState: MutableStateFlow<TitleVideo?> =
        MutableStateFlow(null)

    override val currentVideoState: StateFlow<TitleVideo?>
        get() = mCurrentVideoState.asStateFlow()

    private val mCurrentVideoIdState: MutableStateFlow<UUID> =
        MutableStateFlow(UUID.randomUUID())

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

    override fun loadVideoByUUID(uuid: UUID) {
        Log.d(LOG_TAG, "loadVideoByUUID($uuid)")
        mCurrentVideoIdState.update { uuid }
        return
    }

    override fun addVideo(videoToAdd: TitleVideo) {
        Log.d(LOG_TAG, "adding video $videoToAdd")
        imdbRepo.addVideo(videoToAdd)
    }

    override fun deleteVideo(VideoToDelete: TitleVideo) {
        Log.d(LOG_TAG, "deleting video $VideoToDelete")
        imdbRepo.deleteVideo(VideoToDelete)
    }

}