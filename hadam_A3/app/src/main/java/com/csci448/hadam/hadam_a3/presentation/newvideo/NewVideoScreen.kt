package com.csci448.hadam.hadam_a3.presentation.newvideo

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.TextField
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
    onRequestApiVideo: () -> Unit
) {
    Log.d("LOG_TAG", "New Video Screen")
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(0.3f)
            ) {
//                TextField(value = searchValue, onValueChange = {imd = it})
                NewVideoButton(
                    text = "Request Video",
                    enabled = apiButtonIsEnabled,
                    onClick = onRequestApiVideo
                )
                Spacer(modifier = Modifier.height(16.dp))
                NewVideoButton(
                    text = "Save Video",
                    onClick = {
                        if (titleVideo != null) {
                            onSaveVideo(titleVideo)
                        }
                    }
                )
            }
    }
}