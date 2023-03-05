package edu.mines.csci448.examples.samodelkin.presentation.list

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.mines.csci448.examples.samodelkin.data.SamodelkinCharacter
import edu.mines.csci448.examples.samodelkin.presentation.viewmodel.PreviewSamodelkinViewModel


private const val LOG_TAG = "448.SamodelkinViewModel"

@Composable
fun SamodelkinListScreen(
    characterList: List<SamodelkinCharacter>,
    onSelectCharacter: (SamodelkinCharacter) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(characterList) { character ->
            SamodelkinCharacterListItem(
                character = character,
                onSelectCharacter = onSelectCharacter
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSamodelkinListScreen() {
    Log.d(LOG_TAG, "PreviewSamodelkinListScreen")
    val context = LocalContext.current
    val previewSamodelkinViewModel = PreviewSamodelkinViewModel(context = context)
    SamodelkinListScreen(characterList = previewSamodelkinViewModel.characterListState.collectAsState().value) {}
}