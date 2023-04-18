package com.csci448.hadam.hadam_a3.presentation.newvideo

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
    autoComplete: AutoComplete?,
    searchText: String,
    imdbViewModel: IIMDBViewModel,
    onSaveVideo: () -> Unit,
    apiButtonIsEnabled: Boolean,
    onRequestApiVideo: () -> Unit,
    updateSearchText: (String) -> Unit,
) {
    Log.d("LOG_TAG", "New Video Screen")
    val searchTextChange = remember { mutableStateOf(searchText) }
    Column {
        Row(
            modifier = Modifier
                .fillMaxHeight(.1f)
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier.weight(0.2f)
            ) {
                Row() {
                    Column(modifier = Modifier.fillMaxWidth(.8f)) {
                        TextField(value = searchTextChange.value, onValueChange = {
                            searchTextChange.value = it
                            imdbViewModel.updateSearchState(searchText = searchTextChange.value)
                        })
                    }
                    IconButton(
                        onClick = { onRequestApiVideo() },
                        enabled = apiButtonIsEnabled && searchTextChange.value != "",
                        modifier = Modifier.fillMaxWidth(.8f)
                    ) {
                        Icon(imageVector = Icons.Filled.Search, contentDescription = "")
                    }
                }
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.5f)
                .padding(8.dp)
        ) {

            if (autoComplete != null) {
                Log.d("NewVideoScreen", autoComplete.name)
                items(autoComplete.movies) { it ->
                    NewVideoListScreen(
                        movies = it,
                        onVideoClick = { imdbViewModel.updateSearchVideo(movies = it) })
                }
            } else {
                Log.d("NewVideoScreen", "Nothing in autoComplete")
            }
        }
        val videoToDisplay =
            imdbViewModel.currentSearchVideoToDisplayState.collectAsStateWithLifecycle().value
        NewVideoImage(movies = videoToDisplay)
        NewVideoButton(
            text = "Save Video",
            onClick = {
                onSaveVideo()
                imdbViewModel.updateSearchVideo(null)
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = videoToDisplay != null
        )
    }
}