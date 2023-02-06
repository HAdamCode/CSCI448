package com.csci448.hadam.hadam_a1.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun TotalPizzas(totalPizzas: Int) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 8.dp, bottom = 8.dp))
    {
        Text(text = "Total Pizzas Needed:",
        Modifier.padding(start = 8.dp))
        Text(text = totalPizzas.toString(),
            textAlign = TextAlign.Right,
            modifier = Modifier.fillMaxWidth()
                .padding(end = 8.dp))
    }
}


@Composable
@Preview
fun PreviewTotalPizzas() {
    TotalPizzas(20)
}