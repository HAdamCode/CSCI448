package com.csci448.hadam.hadam_a1.presentation

import android.widget.Toast
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.csci448.hadam.hadam_a1.R
import com.csci448.hadam.hadam_a1.data.Pizza
import com.csci448.hadam.hadam_a1.data.PizzaRepo
import java.lang.StrictMath.ceil

@Composable
fun CalculateButton(vm : PizzaViewModel, onCalculateClick : (Int?) -> Unit) {
    Column (
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
//        val context = ContextAmbient.current
        Button(
            onClick = {onCalculateClick(vm.SetTotals())}, //Toast.makeText(this, "Test", Toast.LENGTH_LONG).show()
            modifier = Modifier.padding(all = Dp(10F)),
            enabled = true,
            border = BorderStroke(width = 1.dp, brush = SolidColor(Color.Blue)),
            shape = MaterialTheme.shapes.large,

        ) {
            Text(text = stringResource(id = R.string.Calculate), color = Color.White)
        }
    }
}

@Composable
@Preview
fun PreviewCalculateButton() {
    var vm = PizzaViewModel(PizzaRepo.pizzas)
    CalculateButton(vm, onCalculateClick = {})
}
