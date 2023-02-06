package com.csci448.hadam.hadam_a1.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.csci448.hadam.hadam_a1.data.PizzaRepo

@Composable
fun PizzaSelect(numSlices: Int) {
    var totalPrice = "%.2f".format(numSlices * PizzaRepo.pizzas.firstOrNull()!!.costPerPie)
    var mExpanded by remember { mutableStateOf(false) }
    val mPizzas = PizzaRepo.pizzas
    var selectedPizza by remember { mutableStateOf(
        "${PizzaRepo.pizzas.firstOrNull()!!.name} " +
                "($${totalPrice} ea)")}
    var mTextFieldSize by remember { mutableStateOf(Size.Zero)}
    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(Modifier.padding(20.dp)) {
        OutlinedTextField(
            value = selectedPizza,
            onValueChange = { selectedPizza = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    mTextFieldSize = coordinates.size.toSize()
                },
            trailingIcon = {
                Icon(icon,"contentDescription",
                    Modifier.clickable { mExpanded = !mExpanded })
            },
        )
        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = { mExpanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current){mTextFieldSize.width.toDp()})
        ) {
            mPizzas.forEach { label ->
                DropdownMenuItem(text = { Text(text = "${label.name} " +
                        "($${"%.2f".format(label.costPerPie * numSlices)} ea)") },
                    onClick = {
                    selectedPizza = "${label.name} " +
                            "($${"%.2f".format(label.costPerPie * numSlices)} ea)"
                    mExpanded = false
                })
            }
        }
    }
}

@Composable
@Preview
fun PreviewPizzaSelect() {
    PizzaSelect(10)
}