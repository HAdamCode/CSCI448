package edu.csci448.beatboxcompose.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.csci448.beatboxcompose.data.Sound

private const val NUM_COLUMNS = 3

@Composable
fun BeatBoxScreen(sounds: List<Sound>, onPlaySound: (Sound) -> Unit) {
    LazyColumn(
        modifier = Modifier.padding(8.dp).fillMaxSize()
    ) {
        items(count = sounds.size / NUM_COLUMNS + 1 ) { rowIndex ->
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                for(i in 0 until NUM_COLUMNS) {
                    val idx = rowIndex * NUM_COLUMNS + i
                    Box(
                        modifier = Modifier
                            .weight(0.33f)
                            .height(128.dp)
                    ) {
                        if (idx < sounds.size) {
                            SoundItemBox(
                                sound = sounds[idx],
                                onPlaySound = onPlaySound
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SoundItemBox(sound: Sound, onPlaySound: (Sound) -> Unit) {
    Button(
        modifier = Modifier.padding(4.dp).fillMaxSize(),
        onClick = { onPlaySound(sound) },
    ) {
        Text(
            text = sound.name,
            fontSize = 24.sp
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewBeatBoxScreen() {
    val sounds = mutableListOf<Sound>()
    for(i in 0..36)
        sounds.add( Sound("${i}_test") )
    BeatBoxScreen(
        sounds = sounds,
        onPlaySound = { }
    )
}