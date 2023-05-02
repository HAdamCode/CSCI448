package com.csci448.hadam.hadam_a4.presentation.settings

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.csci448.hadam.hadam_a4.presentation.viewmodel.IHistoryViewModel
import kotlinx.coroutines.launch
import java.text.DecimalFormat

@Composable
fun SettingsScreen(
    historyViewModel: IHistoryViewModel,
    context: Context
) {
    val saveEnabled = historyViewModel.saveLocationsEnabled.collectAsStateWithLifecycle().value
    Column() {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)) {
            Text(
                text = "Save Locations to Database",
                modifier = Modifier.padding(top = 10.dp, end = 40.dp),
                fontWeight = FontWeight.Bold
            )
            Switch(checked = saveEnabled, onCheckedChange = {
                historyViewModel.updateSaveEnabled()
            })
        }


        Button(
            onClick = {
                historyViewModel.deleteHistory()
                Toast.makeText(
                    context,
                    "History deleted",
                    Toast.LENGTH_LONG
                ).show()
            },
            modifier = Modifier.padding(start = 20.dp, end = 20.dp).fillMaxWidth()
        ) {
            Text(text = "Delete Database")
        }
    }
}