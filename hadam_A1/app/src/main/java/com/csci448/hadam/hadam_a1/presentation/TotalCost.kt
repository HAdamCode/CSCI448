package com.csci448.hadam.hadam_a1.presentation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TotalCost(totalCost: Double) {
    var total = "%.2f".format(totalCost)
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 8.dp, bottom = 8.dp))
    {
        Text(text = "Total Cost:",
            Modifier.padding(start = 8.dp))
        Text(text = ("%.2f".format(totalCost)),
            textAlign = TextAlign.Right,
            modifier = Modifier.fillMaxWidth()
                .padding(end = 8.dp))
    }
}

@Composable
@Preview
fun PreviewTotalCost() {
    TotalCost(40.20)
}