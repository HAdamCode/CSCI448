package edu.csci448.beatboxcompose.presentation

import androidx.compose.ui.test.*
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import edu.csci448.beatboxcompose.data.Sound
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
internal class BeatBoxScreenKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        val sounds = mutableListOf<Sound>()
        for (i in 0..36) {
            sounds.add(Sound("${i}_test"))
        }

        composeTestRule.setContent {
            BeatBoxScreen(
                sounds = sounds,
                onPlaySound = { }
            )
        }
    }

    @Test
    fun buttonHasSoundNameAsLabel() {
        composeTestRule.onNode(hasText("1_test")).assertIsDisplayed()
    }
}