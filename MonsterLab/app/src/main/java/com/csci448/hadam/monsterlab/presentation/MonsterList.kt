package com.csci448.hadam.monsterlab.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.csci448.hadam.monsterlab.data.Monster
import com.csci448.hadam.monsterlab.data.MonsterRepo

// Dynamic way of grabbing data from mock database, ie list of monster
@Composable
fun MonsterList(monsters : List<Monster>, onMonsterClicked: (Monster) -> Unit) {
    LazyColumn() {
        item (monsters) {
            for (monster: Monster in monsters) {
                MonsterCard(monster = monster, onMonsterClicked)
            }
        }
    }
}

// Preview to show the list of monsters will show up
@Preview
@Composable
fun Monsters() {
    MonsterList(monsters = MonsterRepo.monsters, onMonsterClicked = {})
}