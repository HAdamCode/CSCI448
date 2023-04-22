package edu.csci448.beatboxcompose.util

import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager
import android.media.SoundPool
import android.util.Log
import edu.csci448.beatboxcompose.data.Sound
import java.io.IOException

class BeatBox(private val assets: AssetManager) {
    companion object {
        private const val LOG_TAG = "448.BeatBox"
        private const val SOUNDS_FOLDER = "sample_sounds"
        private const val MAX_SOUNDS = 5
    }

    val sounds: List<Sound>
    private val mSoundPool = SoundPool.Builder()
        .setMaxStreams(MAX_SOUNDS)
        .build()

    init {
        sounds = loadSounds()
        Log.d(LOG_TAG, "BeatBox initialized")
    }

    fun play(sound: Sound) {
        sound.soundId?.let { soundId ->
            mSoundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f)
        }
    }

    fun release() {
        Log.d(LOG_TAG, "release() called, releasing soundPool")
        mSoundPool.release()
    }

    private fun loadSounds(): List<Sound> {
        val soundNames: Array<String>

        try {
            soundNames = assets.list(SOUNDS_FOLDER)!!
            Log.d(LOG_TAG, "Found ${soundNames.size} sounds}")
        } catch (e: Exception) {
            Log.e(LOG_TAG, "Could not list assets", e)
            return emptyList()
        }

        val sounds = mutableListOf<Sound>()
        soundNames.forEach { filename ->
            val assetPath = "$SOUNDS_FOLDER/$filename"
            val sound = Sound(assetPath)
            try {
                load(sound)
                sounds.add(sound)
            } catch (ioe: IOException) {
                Log.e(LOG_TAG, "Could not load sound $filename", ioe)
            }
        }
        return sounds
    }

    private fun load(sound: Sound) {
        val afd: AssetFileDescriptor = assets.openFd(sound.assetPath)
        val soundId = mSoundPool.load(afd, 1)
        sound.soundId = soundId
    }
}