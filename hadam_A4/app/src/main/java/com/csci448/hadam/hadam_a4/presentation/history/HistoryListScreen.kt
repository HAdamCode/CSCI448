package com.csci448.hadam.hadam_a4.presentation.history

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.csci448.hadam.hadam_a4.data.History

@Composable
fun HistoryListScreen(
    histories: List<History>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(histories) { history ->
            HistoryListItem(
                history = history,
            )
        }
    }
}