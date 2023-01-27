package com.csci448.hadam.monsterlab.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.csci448.hadam.monsterlab.data.Monster

// View model for the monsters
class MonsterViewModel {
    constructor(monsters: List<Monster>) {
    }

    // Row state of the monsters
    val selectedMonsterState: MutableState<Monster?> = mutableStateOf(null)
}