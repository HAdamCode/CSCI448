package com.csci448.hadam.hadam_a4.presentation.viewmodel

import android.location.Location
import android.util.Log
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csci448.hadam.hadam_a4.data.History
import com.csci448.hadam.hadam_a4.data.HistoryRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HistoryViewModel(private val historyRepo: HistoryRepo) : IHistoryViewModel, ViewModel() {
    companion object {
        private const val LOG_TAG = "448.IMDBViewModel"
    }

    private val mHistories: MutableStateFlow<List<History>> =
        MutableStateFlow(emptyList())

    override val historyListState: StateFlow<List<History>>
        get() = mHistories.asStateFlow()

    private val mCurrentHistoryState: MutableStateFlow<History?> =
        MutableStateFlow(null)

    override val currentHistoryState: StateFlow<History?>
        get() = mCurrentHistoryState.asStateFlow()

    private val mCurrentHistoryIdState: MutableStateFlow<String> =
        MutableStateFlow("")

    private val mCurrentDrawerState: MutableStateFlow<DrawerState> =
        MutableStateFlow(DrawerState(DrawerValue.Closed))
    override val currentDrawerState: StateFlow<DrawerState>
        get() = mCurrentDrawerState.asStateFlow()

    private val mSaveLocationsEnabled: MutableStateFlow<Boolean> =
        MutableStateFlow(true)
    override val saveLocationsEnabled: StateFlow<Boolean>
        get() = mSaveLocationsEnabled.asStateFlow()

    private val mCurrentLocation: MutableStateFlow<Location?> =
        MutableStateFlow(null)
    override val currentLocation: StateFlow<Location?>
        get() = mCurrentLocation.asStateFlow()

    private val mCurrentLocationList: MutableStateFlow<ArrayList<History?>> =
        MutableStateFlow(arrayListOf())
    override val currentLocationList: StateFlow<ArrayList<History?>>
        get() = mCurrentLocationList.asStateFlow()


    init {
        viewModelScope.launch {
            historyRepo.getHistories().collect { historyList ->
                mHistories.update { historyList }
            }
        }
        viewModelScope.launch {
            mCurrentHistoryIdState
                .map { uuid -> historyRepo.getHistory(uuid) }
                .collect { history -> mCurrentHistoryState.update { history } }
        }
    }

    override fun loadHistoryByUUID(uuid: String) {
        Log.d(LOG_TAG, "loadHistoryByUUID($uuid)")
        mCurrentHistoryIdState.update { uuid }
        return
    }

    override fun addHistory(historyToAdd: History) {
        Log.d(LOG_TAG, "adding video $historyToAdd")
        historyRepo.addHistory(historyToAdd)
    }

    override fun deleteHistory() {
        Log.d(LOG_TAG, "deleting history")
        mHistories.value.forEach {historyToDelete ->
            historyRepo.deleteHistory(historyToDelete)
        }
    }

    override fun updateSaveEnabled() {
        val enabled = mSaveLocationsEnabled.value
        mSaveLocationsEnabled.value = !enabled
    }

    override fun updateCurrentLocation(history: History?) {
        mCurrentHistoryState.value = history
    }

    override fun updateCurrentLocationList(history: History?) {
//        var hist: List<History>
//        mCurrentLocationList.value.forEach {
//            hist.ap
//        }
        mCurrentLocationList.value.add(history)
    }
}