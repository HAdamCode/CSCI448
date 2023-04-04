package edu.mines.csci448.examples.samodelkin.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import edu.mines.csci448.examples.samodelkin.data.SamodelkinCharacter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.*

class SamodelkinViewModel(characters: List<SamodelkinCharacter>) : ViewModel() {
    companion object {
        private const val LOG_TAG = "448.SamodelkinViewModel"
    }

    private val mCharacters = characters.toMutableStateList()

    /**
     * holds list of all characters stored within the view model
     */
    val characters: List<SamodelkinCharacter>
        get() = mCharacters.toList()

    private val mCurrentCharacterState: MutableStateFlow<SamodelkinCharacter?> = MutableStateFlow(null)

    val currentCharacterState: StateFlow<SamodelkinCharacter?>
        get() = mCurrentCharacterState.asStateFlow()

    /**
     * Loads a character by id into currentCharacterState, if it exists.  If id is not found
     * in list of characters, then sets currentCharacterState to null.
     * @param uuid id to use for character lookup
     */
    fun loadCharacterByUUID(uuid: UUID) {
        Log.d(LOG_TAG, "loadCharacterByUUID($uuid)")
        mCurrentCharacterState.value = null
        mCharacters.forEach { character ->
            if(character.id == uuid) {
                Log.d(LOG_TAG, "Character found! $character")
                mCurrentCharacterState.value = character
                return
            }
        }
        Log.d(LOG_TAG, "Character not found")
        return
    }

    /**
     * Adds the given character to the list of characters.
     * @param characterToAdd character to add to the list
     */
    fun addCharacter(characterToAdd: SamodelkinCharacter) {
        Log.d(LOG_TAG, "adding character $characterToAdd")
        mCharacters += characterToAdd
    }

    /**
     * Deletes corresponding character from the list of characters, if it exists in the list.
     * Matches characters by id.  If character is not found in the list, does nothing.
     * @param characterToDelete character to delete from list
     */
    fun deleteCharacter(characterToDelete: SamodelkinCharacter) {
        Log.d(LOG_TAG, "deleting character $characterToDelete")
        mCharacters.forEach { character ->
            if(character.id == characterToDelete.id) {
                mCharacters.remove(character)
                if(mCurrentCharacterState.value == character) {
                    mCurrentCharacterState.value = null
                }
                Log.d(LOG_TAG, "character deleted")
                return
            }
        }
        Log.d(LOG_TAG, "Character not found")
    }
}