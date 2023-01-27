@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.csci448.hadam.monsterlab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.csci448.hadam.monsterlab.ui.theme.MonsterLabTheme
import com.csci448.hadam.monsterlab.presentation.MonsterCard
import androidx.compose.ui.Modifier
import com.csci448.hadam.monsterlab.data.Monster
import com.csci448.hadam.monsterlab.data.MonsterRepo
import com.csci448.hadam.monsterlab.presentation.MonsterDisplay
import com.csci448.hadam.monsterlab.presentation.MonsterList
import com.csci448.hadam.monsterlab.presentation.MonsterViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var vm  = MonsterViewModel(MonsterRepo.monsters)
        setContent {
            MonsterLabTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    MonsterLabScreen(vm)
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    var vm  = MonsterViewModel(MonsterRepo.monsters)
    MonsterLabTheme{
        MonsterLabScreen(vm)
    }
}



//@Composable
//fun MonsterLabScreen() {
//    MonsterCard()
//}

@Composable
fun MonsterLabScreen(vm : MonsterViewModel) {
    Column {
        Box(modifier = Modifier
            .weight(0.5f)
            .fillMaxSize()
        )

        {
                MonsterList(MonsterRepo.monsters) {
                    vm.selectedMonsterState.value = it
                }
        }
        Box(modifier = Modifier
            .weight(0.5f)
            .fillMaxSize()
        )

        {
            MonsterDisplay(vm.selectedMonsterState.value)
        }
    }
}