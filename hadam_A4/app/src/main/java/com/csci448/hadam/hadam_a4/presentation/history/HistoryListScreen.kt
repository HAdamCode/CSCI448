package com.csci448.hadam.hadam_a4.presentation.history

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DismissDirection
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.csci448.hadam.hadam_a4.R
import com.csci448.hadam.hadam_a4.data.History

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HistoryListScreen(
    histories: List<History>,
    dismissed: (history: History) -> Unit
) {
    var willDismissDirection: DismissDirection? by remember {
        mutableStateOf(null)
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(histories, { history: History -> history.id }) { history ->
            val state = rememberDismissState(
                confirmStateChange = {
                    Log.d("test2", histories.size.toString())
                    if (willDismissDirection == DismissDirection.StartToEnd) {
                        dismissed(history)
                        willDismissDirection == DismissDirection.StartToEnd
                    } else {
                        false
                    }
                }
            )
            LaunchedEffect(key1 = Unit, block = {
                snapshotFlow { state.offset.value }
                    .collect {
                        willDismissDirection = when {
                            it > 400f -> {
                                DismissDirection.StartToEnd
                            }

                            else -> null
                        }
                    }
            })
            SwipeToDismiss(
                dismissThresholds = {
                    if (it == DismissDirection.StartToEnd) {
                        FractionalThreshold(1.5f)
                    } else {
                        FractionalThreshold(1.5f)
                    }
                },
                background = {
                    androidx.compose.material3.Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = stringResource(R.string.about_name),
                        modifier = Modifier.padding(top = 20.dp, start = 20.dp)
                    )
                },
                state = state,
            )
            {
                HistoryListItem(
                    history = history,
                )
            }
        }
    }
}