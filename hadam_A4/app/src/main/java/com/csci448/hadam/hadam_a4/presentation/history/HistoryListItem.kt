package com.csci448.hadam.hadam_a4.presentation.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csci448.hadam.hadam_a4.data.History
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun HistoryListItem(
    history: History
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
//        onClick = { onVideoClick(video) }
    ) {
        val current =
            history.dateTime.format(DateTimeFormatter.ofPattern("MM/dd/yy HH:mm"))
        Row() {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp)
            ) {
                Row(modifier = Modifier
                    .fillMaxWidth()) {
                    Text(text = "Checked In: ", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                    Text(text = current, fontSize = 13.sp)
                }
                Row() {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(.5f)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "Latitude: ",
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp
                            )
                            Text(text = history.lat.toString(), fontSize = 13.sp)
                        }
                    }
                    Column() {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "Longitude: ",
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp
                            )
                            Text(text = history.lon.toString(), fontSize = 13.sp)
                        }
                    }
                }
                Row() {
                    Column(modifier = Modifier
                        .fillMaxWidth(.5f)) {
                        Row() {
                            Text(text = "Temperature: ", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                            Text(text = history.temp.toString(), fontSize = 13.sp)
                        }
                    }
                    Column() {
                        Row() {
                            Text(text = "Weather: ", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                            Text(text = history.description, fontSize = 13.sp)
                        }
                    }
                }
            }
        }
    }
}