package com.csci448.hadam.monsterlab.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.csci448.hadam.monsterlab.data.Monster

class MonsterViewModel {
    constructor(monsters: List<Monster>) {
    }

    val selectedMonsterState: MutableState<Monster?> = mutableStateOf(null)
}