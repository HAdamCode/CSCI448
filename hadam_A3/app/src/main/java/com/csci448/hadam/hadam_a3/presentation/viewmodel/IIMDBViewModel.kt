package com.csci448.hadam.hadam_a3.presentation.viewmodel

import com.csci448.hadam.hadam_a3.data.titlevideo.TitleVideo
import kotlinx.coroutines.flow.StateFlow
import java.util.*

interface IIMDBViewModel {
    val videoListState: StateFlow<List<TitleVideo>>
    val currentVideoState: StateFlow<TitleVideo?>
    val currentVideoSearchState: StateFlow<String?>
    fun loadVideoByUUID(uuid: UUID)
    fun addVideo(videoToAdd: TitleVideo)
    fun deleteVideo(videoToDelete: TitleVideo)
}