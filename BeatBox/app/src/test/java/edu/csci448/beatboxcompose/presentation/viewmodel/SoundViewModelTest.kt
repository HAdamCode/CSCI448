package edu.csci448.beatboxcompose.presentation.viewmodel

import edu.csci448.beatboxcompose.data.Sound
import edu.csci448.beatboxcompose.util.BeatBox
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify


class SoundViewModelTest {

    private lateinit var sound: Sound
    private lateinit var beatBox: BeatBox
    private lateinit var subject: SoundViewModel

    @Before
    fun setUp() {
        sound = Sound("folder/assetPath.wav")
        beatBox = mock(BeatBox::class.java)
        subject = SoundViewModel(beatBox)
    }

    @Test
    fun exposesFilenameAsSoundName() {
        assertThat(sound.name, `is`("assetPath"))
    }

    @Test
    fun onPlaySoundCallsBeatBoxPlay() {
        subject.onPlaySound(sound)
        verify(beatBox).play(sound)
    }
}