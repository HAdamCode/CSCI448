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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.csci448.hadam.hadam_a3.R
import com.csci448.hadam.hadam_a3.data.Video
import com.csci448.hadam.hadam_a3.data.autocomplete.AutoComplete
import com.csci448.hadam.hadam_a3.presentation.viewmodel.IIMDBViewModel

@Composable
fun NewVideoScreen(
//    autoComplete: AutoComplete?,
    searchText: String,
    imdbViewModel: IIMDBViewModel,
    onSaveVideo: () -> Unit,
    apiButtonIsEnabled: Boolean,
    onRequestApiVideo: () -> Unit,
    updateSearchText: (String) -> Unit,
) {
    Log.d("LOG_TAG", "New Video Screen")
    var searchTextChange = remember { mutableStateOf(searchText) }
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(0.3f)
        ) {
            TextField(value = searchTextChange.value, onValueChange = {
                searchTextChange.value = it
                imdbViewModel.updateSearchState(searchText = searchTextChange.value)
            })
            NewVideoButton(
                text = "Request Video",
                enabled = apiButtonIsEnabled,
                onClick = onRequestApiVideo
            )
            Spacer(modifier = Modifier.height(16.dp))
            NewVideoButton(
                text = "Save Video",
                onClick = {
//                        if (autoComplete != null) {
//                            onSaveVideo(autoComplete)
//                        }
                    onSaveVideo()
                }
            )
        }
    }
}