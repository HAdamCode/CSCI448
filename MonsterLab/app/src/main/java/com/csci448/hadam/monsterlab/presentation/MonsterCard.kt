@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.csci448.hadam.monsterlab.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csci448.hadam.monsterlab.R
import com.csci448.hadam.monsterlab.data.Monster

// Takes in a monster and an action. If the monster is clicked, the onMonsterClicked is fired.
@Composable
fun MonsterCard(monster: Monster, onMonsterClicked: (Monster) -> Unit) {
    Card(modifier = Modifier.padding(8.dp), onClick = { onMonsterClicked(monster) })
    {
        Row(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.primaryContainer)
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp, start = 4.dp, end = 4.dp)
        )
        {
            Image(
                painter = painterResource(
                    id =
                    monster.imageId
                ),
                contentDescription = stringResource(id = R.string.name_monster_mike)
            )
            Column(modifier = Modifier.padding(4.dp))
            {
                Text(
                    text = stringResource(id = monster.nameId),
                    fontSize = 28.sp,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = stringResource(id = monster.descId),
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
    }
}

// Preview to show a monster card
@Preview
@Composable
fun PreviewMonsterCard() {
    val youngMike = Monster(
        imageId = R.drawable.monsters_university_character_young_mike_icons,
        nameId = R.string.name_monster_mike,
        descId = R.string.description_monster_mike
    )
    MonsterCard(youngMike, onMonsterClicked = {})
}

// Hard coded preview for second monster
@Preview
@Composable
fun PreviewMonsterCard2() {
    val donCarlton = Monster(
        imageId = R.drawable.monsters_university_don_carlton_icon,
        nameId = R.string.name_monster_don_carlton,
        descId = R.string.description_monster_don_carlton
    )
    MonsterCard(donCarlton, onMonsterClicked = {})
}




