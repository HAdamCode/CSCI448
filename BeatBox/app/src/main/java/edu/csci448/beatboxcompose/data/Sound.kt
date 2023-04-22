package edu.csci448.beatboxcompose.data

class Sound(val assetPath: String, var soundId: Int? = null) {
    companion object {
        private const val WAV = ".wav"
    }
    val name = assetPath.split("/").last().removeSuffix(WAV)
}