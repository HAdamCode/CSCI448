package com.csci448.hadam.hadam_a1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.csci448.hadam.hadam_a1.presentation.*
import com.csci448.hadam.hadam_a1.ui.theme.Hadam_A1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vm = PizzaViewModel()
        setContent {
            Hadam_A1Theme {
                Surface(modifier = Modifier.fillMaxSize()) { PizzaScreen(vm) }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val vm = PizzaViewModel()
    Hadam_A1Theme {
        PizzaScreen(vm)
    }
}

@Composable
fun PizzaScreen(vm : PizzaViewModel) {
    Column {
        vm.initTotals()
        NumPeople(vm)
        Hunger(vm)
        PizzaSelect(vm)
        CalculateButton(vm) {vm.selectedTotalPizzaState.value = it}
        TotalPizzas(vm.selectedTotalPizzaState.value)
        TotalCost(vm.selectedTotalCostState.value)
    }
}