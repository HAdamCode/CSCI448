package com.csci448.hadam.hadam_a1.data

import com.csci448.hadam.hadam_a1.R

object PizzaRepo {
    var pizzas =
        listOf(
            Pizza(R.string.Pepperoni, 2.12),
            Pizza(R.string.Vegetarian, 3.42),
            Pizza(R.string.Vegan, 5.01),
            Pizza(R.string.Meat_Lovers, 2.13),
            Pizza(R.string.Cheese, 2.06),
            Pizza(R.string.Ham_and_Pineapple, 10.97)
        )
}