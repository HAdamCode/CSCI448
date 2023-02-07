package com.csci448.hadam.hadam_a1.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.csci448.hadam.hadam_a1.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Hunger(vm : PizzaViewModel) {
    val options = listOf(stringResource(id = R.string.Light),
        stringResource(id = R.string.Medium),
        stringResource(id = R.string.Ravenous))
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(options[2]) }
    vm.selectedHungerState.value = selectedOption
    Row {
        Column {
            Text(text = stringResource(id = R.string.How_hungry_is_everyone),
                Modifier
                    .padding(top = 18.dp, bottom = 0.dp, start = 4.dp, end = 4.dp)
                    .background(color = MaterialTheme.colorScheme.primary)
            )
            Row {
                options.forEach { text ->
                    Row(
                        Modifier
                            .selectable(
                                selected = (text == selectedOption),
                                onClick = { onOptionSelected(text) }
                            )
                            .padding(horizontal = 10.dp)
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
                            modifier = Modifier.padding(top = 14.dp, start = 2.dp)
                        )
                    }
                }
            }
        }

    }
}

@Composable
@Preview
fun PreviewHunger() {
    val vm = PizzaViewModel()
    Hunger(vm)
}