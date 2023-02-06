package com.csci448.hadam.hadam_a1.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NumPeople() {
    val inputValue = remember { mutableStateOf(TextFieldValue()) }
    Row(modifier = Modifier.background(color = MaterialTheme.colorScheme.primary).fillMaxWidth()) {
        Text(
            text = "Number of People?",
            Modifier.padding(top = 18.dp, bottom = 0.dp, start = 4.dp, end = 4.dp)
                .background(color = MaterialTheme.colorScheme.primary)
        )
        TextField(
            value = inputValue.value,
            onValueChange = { inputValue.value = it },
            placeholder = {Text(text = "0")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            maxLines = 1,
            singleLine = true,
            modifier = Modifier.background(color = MaterialTheme.colorScheme.primaryContainer)
        )
    }
}

@Composable
@Preview
fun PreviewNumPeople() {
    NumPeople()
}
