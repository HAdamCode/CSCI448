package com.csci448.hadam.hadam_a1.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class PizzaViewModel {
    val selectedPriceState: MutableState<Double?> = mutableStateOf(null)
    val selectedHungerState: MutableState<String?> = mutableStateOf(null)
    val selectedNumPeopleState: MutableState<Int?> = mutableStateOf(null)
    val selectedTotalPizzaState: MutableState<Int?> = mutableStateOf(null)
    val selectedTotalCostState: MutableState<Double?> = mutableStateOf(null)

    fun initTotals() {
        if (selectedTotalPizzaState.value == null) {
            selectedTotalPizzaState.value = 0
        }
        if (selectedTotalCostState.value == null) {
            selectedTotalCostState.value = 0.0
        }
    }

    fun setTotals(): Int?  {
        when (selectedHungerState.value) {
            "Light" -> {
                selectedTotalPizzaState.value = StrictMath.ceil(
                    selectedNumPeopleState.value?.div(
                        8.0
                    ) ?: 0.0
                ).toInt()
            }
            "Medium" -> {
                selectedTotalPizzaState.value = StrictMath.ceil(
                    (selectedNumPeopleState.value?.times(2))?.div(8.0) ?: 0.0
                ).toInt()
            }
            else -> {
                selectedTotalPizzaState.value = StrictMath.ceil(
                    (selectedNumPeopleState.value?.times(
                        4
                    ))?.div(8.0) ?: 0.0
                ).toInt()
            }
        }
        if (selectedNumPeopleState.value == null) {
            selectedTotalPizzaState.value = 0
        }
        else {
            selectedTotalCostState.value = selectedTotalPizzaState.value?.times(selectedPriceState.value!!)
        }

        return selectedTotalPizzaState.value
    }

}