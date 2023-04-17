package com.csci448.hadam.hadam_a3.presentation.newvideo

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.csci448.hadam.hadam_a3.R
import com.csci448.hadam.hadam_a3.data.titlevideo.TitleVideo

@Composable
fun NewVideoScreen(
    titleVideo: TitleVideo?,
    onSaveVideo: (TitleVideo) -> Unit,
    apiButtonIsEnabled: Boolean,
    onRequestApiCharacter: () -> Unit
) {
    when (LocalConfiguration.current.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
//                Card(
//                    modifier = Modifier.weight(0.7f)
//                ) {
//                    SamodelkinCharacterDetails(character = character)
//                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier.weight(0.3f)
                ) {
                    NewVideoButton(
                        text = stringResource(R.string.app_name),
                        enabled = apiButtonIsEnabled,
                        onClick = onRequestApiCharacter
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    NewVideoButton(
                        text = stringResource(R.string.app_name),
                        onClick = {
                            if (titleVideo != null) {
                                onSaveVideo(titleVideo)
                            }
                        }
                    )
                }
            }
        }
        else -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
//                Card {
//                    SamodelkinCharacterDetails(character = character)
//                }
                Spacer(modifier = Modifier.height(16.dp))
                Row {
//                    Box(
//                        modifier = Modifier.weight(0.5f)
//                    ) {
//                        NewVideoButton(
//                            text = stringResource(R.string.app_name),
//                            onClick = onGenerateRandomCharacter,
//                        )
//                    }
//                    Spacer(modifier = Modifier.width(16.dp))
                    Box(
                        modifier = Modifier.weight(0.5f)
                    ) {
                        NewVideoButton(
                            text = stringResource(R.string.app_name),
                            enabled = apiButtonIsEnabled,
                            onClick = onRequestApiCharacter
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                NewVideoButton(
                    text = stringResource(R.string.app_name),
                    onClick = {
                        if (titleVideo != null) {
                            onSaveVideo(titleVideo)
                        }
                    }
                )
            }
        }
    }
}