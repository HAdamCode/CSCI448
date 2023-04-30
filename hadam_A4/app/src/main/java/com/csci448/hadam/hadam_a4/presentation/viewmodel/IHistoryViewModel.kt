package com.csci448.hadam.hadam_a4.presentation.viewmodel

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import com.csci448.hadam.hadam_a4.data.History
import kotlinx.coroutines.flow.StateFlow

interface IHistoryViewModel {
    val historyListState: StateFlow<List<History>>
    val currentHistoryState: StateFlow<History?>
    val currentDrawerState: StateFlow<DrawerState>
    fun loadHistoryByUUID(uuid: String)
    fun addHistory(historyToAdd: History)
    fun deleteHistory(historyToDelete: History)
}