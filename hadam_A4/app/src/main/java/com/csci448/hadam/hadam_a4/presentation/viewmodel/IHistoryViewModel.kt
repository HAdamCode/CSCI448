package com.csci448.hadam.hadam_a4.presentation.viewmodel

import android.location.Location
import androidx.compose.material3.DrawerState
import com.csci448.hadam.hadam_a4.data.History
import kotlinx.coroutines.flow.StateFlow

interface IHistoryViewModel {
    val historyListState: StateFlow<List<History>>
    val currentHistoryState: StateFlow<History?>
    val currentDrawerState: StateFlow<DrawerState>
    val saveLocationsEnabled: StateFlow<Boolean>
    val currentLocation: StateFlow<Location?>
    val currentLocationList: StateFlow<ArrayList<History?>>
    fun loadHistoryByUUID(uuid: String)
    fun addHistory(historyToAdd: History)
    fun deleteHistory(historyToDelete: History)
    fun updateSaveEnabled()
    fun updateCurrentLocation(history: History?)
    fun updateCurrentLocationList(history: History?)
}