package com.csci448.hadam.hadam_a1.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Hunger() {
    val options = listOf("Light", "Medium", "Ravenous")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(options[2]) }
    Row() {
        Column() {
            Text(text = "How hungry is everyone?",
                Modifier.padding(top = 18.dp, bottom = 0.dp, start = 4.dp, end = 4.dp)
                    .background(color = MaterialTheme.colorScheme.primary)
            )
            Row() {
                options.forEach { text ->
                    Row(
                        Modifier
                            .selectable(
                                selected = (text == selectedOption),
                                onClick = { onOptionSelected(text) }
                            )
                            .padding(horizontal = 16.dp)
                    ) {
//                        val context = ContextAmbient.current
                        RadioButton(
                            selected = (text == selectedOption),

                            onClick = {
                                onOptionSelected(text)
//                                Toast.makeText(context, text, Toast.LENGTH_LONG).show()
                            }
                        )
                        Text(
                            text = text,
                            modifier = Modifier.padding(top = 14.dp, start = 4.dp)
                        )
                    }
                }
            }
        }

    }
}

@Composable
@Preview
fun PreivewHunger() {
    Hunger()
}

@Composable
fun RadioButtonComponent() {


}