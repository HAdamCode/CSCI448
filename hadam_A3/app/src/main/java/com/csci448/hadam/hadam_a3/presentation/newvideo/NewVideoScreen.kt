package com.csci448.hadam.hadam_a3.presentation.newvideo

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
    val focusManager = LocalFocusManager.current

    Column {
        Row(
            modifier = Modifier
                .fillMaxHeight(.1f)
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            val hideKeyboard = remember {
                mutableStateOf(false)
            }
            Column(
                modifier = Modifier
                    .weight(0.2f)

            ) {
                Row() {

                    Column(modifier = Modifier.fillMaxWidth(.8f)) {
                        TextField(value = searchTextChange.value, onValueChange = {
                            searchTextChange.value = it
                            imdbViewModel.updateSearchState(searchText = searchTextChange.value)
                        },
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                            trailingIcon = {
                                Icon(Icons.Default.Clear,
                                    contentDescription = "clear text",
                                    modifier = Modifier
                                        .clickable {
                                            searchTextChange.value = ""
                                        }
                                )
                            }
                        )
                    }
                    IconButton(
                        onClick = {
                            onRequestApiVideo()
                            focusManager.clearFocus()
                        },
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