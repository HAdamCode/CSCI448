package edu.csci448.beatboxcompose.presentation.viewmodel

import androidx.lifecycle.ViewModel
import edu.csci448.beatboxcompose.util.BeatBox
import edu.csci448.beatboxcompose.data.Sound

class SoundViewModel(private val beatBox: BeatBox) : ViewModel() {
    val sounds = beatBox.sounds

    fun onPlaySound(sound: Sound?) {
        // TODO have the BeatBox play the sound!
    }

    override fun onCleared() {
        super.onCleared()
        beatBox.release()
    }
}