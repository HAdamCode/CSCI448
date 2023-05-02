package com.csci448.hadam.hadam_a4.presentation.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.csci448.hadam.hadam_a4.presentation.viewmodel.IHistoryViewModel
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(
    historyViewModel: IHistoryViewModel
) {
    val saveEnabled = historyViewModel.saveLocationsEnabled.collectAsStateWithLifecycle().value
    Column() {
        Switch(checked = saveEnabled, onCheckedChange = {
            historyViewModel.updateSaveEnabled()
        })

        Button(
            onClick = {
//                newData.value = "hybrid"
//                lifecycleOwner.lifecycleScope.launch { dataStoreManager.setData1(newData.value) }
                      historyViewModel.deleteHistory()
            },
//            modifier = Modifier
//                .width(120.dp)
//                .padding(end = 10.dp),
        ) {
            Text(text = "Delete Database")
        }
    }
}