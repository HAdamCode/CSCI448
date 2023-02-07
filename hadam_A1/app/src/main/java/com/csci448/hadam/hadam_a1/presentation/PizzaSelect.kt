package com.csci448.hadam.hadam_a1.presentation

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.csci448.hadam.hadam_a1.data.PizzaRepo

@Composable
fun PizzaSelect(vm : PizzaViewModel) {
    val totalPrice = "%.2f".format(PizzaRepo.pizzas.firstOrNull()!!.costPerPie)
    var mExpanded by remember { mutableStateOf(false) }
    val mPizzas = PizzaRepo.pizzas
    val initText = "${stringResource(id = PizzaRepo.pizzas.firstOrNull()!!.name)} " +
    "($${totalPrice} ea)"
    var selectedPizza by remember { mutableStateOf(
        initText)}
    var mTextFieldSize by remember { mutableStateOf(Size.Zero)}
    var price by remember { mutableStateOf(PizzaRepo.pizzas.first().costPerPie) }
    vm.selectedPriceState.value = price
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
                val text = "${stringResource(id = label.name)} " +
                        "($${"%.2f".format(label.costPerPie)} ea)"
                DropdownMenuItem(text = { Text(text = text) },
                    onClick = {
                    selectedPizza = text
                    mExpanded = false
                        price = "%.2f".format(label.costPerPie).toDouble()
                })
            }
        }
    }
}

@Composable
@Preview
fun PreviewPizzaSelect() {
    val vm = PizzaViewModel()
    PizzaSelect(vm)
}