@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.csci448.hadam.monsterlab.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csci448.hadam.monsterlab.R
import com.csci448.hadam.monsterlab.data.Monster

@Composable
fun MonsterDisplay(monster : Monster?) {
    if (monster != null) {
       Card(modifier = Modifier.padding(8.dp))
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
                   contentDescription = stringResource(id = R.string.name_monster_mike),
                   modifier = Modifier.fillMaxSize()
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
    else {
        Text (
           text = stringResource(id = R.string.null_monster_check),
           fontSize = 30.sp,
           color = MaterialTheme.colorScheme.primaryContainer
        )
    }

}

@Preview
@Composable
fun Preview1() {
    MonsterDisplay(monster = Monster(
        R.drawable.monsters_university_character_young_mike_icons,
        R.string.name_monster_mike,
        R.string.description_monster_mike
    ))
}

@Preview
@Composable
fun Preview2() {
    MonsterDisplay(monster = null)
}