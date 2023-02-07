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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.getSelectedText
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.csci448.hadam.hadam_a1.R
import com.csci448.hadam.hadam_a1.data.PizzaRepo

@Composable
fun NumPeople(vm : PizzaViewModel) {
    val inputValue = remember { mutableStateOf("0") }
    vm.selectedNumPeopleState.value = inputValue.value.toIntOrNull()
    Row(modifier = Modifier
        .background(color = MaterialTheme.colorScheme.primary)
        .fillMaxWidth()) {
        Text(
            text = stringResource(id = R.string.Num_People_Question),
            Modifier
                .padding(top = 18.dp, bottom = 0.dp, start = 4.dp, end = 4.dp)
                .background(color = MaterialTheme.colorScheme.primary)
        )
        TextField(
            value = inputValue.value,
            onValueChange = { inputValue.value = it },
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
    var vm = PizzaViewModel(PizzaRepo.pizzas)
    NumPeople(vm)
}
