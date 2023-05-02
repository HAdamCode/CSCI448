package com.csci448.hadam.hadam_a4.presentation.settings

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.csci448.hadam.hadam_a4.presentation.viewmodel.IHistoryViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.text.DecimalFormat

@Composable
fun SettingsScreen(
    historyViewModel: IHistoryViewModel,
    context: Context,
    coroutineScope: CoroutineScope
) {
    val saveEnabled = historyViewModel.saveLocationsEnabled.collectAsStateWithLifecycle(context = coroutineScope.coroutineContext).value
    val histories = historyViewModel.currentLocationList.collectAsStateWithLifecycle().value
    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 40.dp)
        ) {
            Text(
                text = "Save Locations to Database",
                modifier = Modifier.padding(top = 10.dp, end = 40.dp),
                fontWeight = FontWeight.Bold
            )
            Switch(checked = saveEnabled, onCheckedChange = {
                historyViewModel.updateSaveEnabled()
            })
        }

        val showDialog = remember { mutableStateOf(false) }
        Button(
            onClick = {
                showDialog.value = true

            },
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Delete Database")
        }
        if (showDialog.value) {
            AlertDialog(
                onDismissRequest = { showDialog.value = false },
                dismissButton = {
                    Button(
                        onClick = {
                            showDialog.value = false
                        },
                        content = { Text(text = "No") }
                    )
                },
                title = { Text(text = "Delete Database") },
                text = { Text(text = "Are you sure you want to delete the database?") },
                confirmButton = {
                    Button(
                        onClick = {
                            showDialog.value = false

                            historyViewModel.deleteHistory()
                            Toast.makeText(
                                context,
                                "History deleted",
                                Toast.LENGTH_LONG
                            ).show()

                            if (histories.isEmpty()){
                                historyViewModel.updateCurrentLocation(null)
                            }
                            else {
                                historyViewModel.updateCurrentLocation(histories.first())
                            }
                        },
                        content = { Text(text = "Yes") }
                    )
                }
            )
        }
    }
}