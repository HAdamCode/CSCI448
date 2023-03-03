package edu.mines.csci448.examples.samodelkin.data

import android.content.Context
import android.util.Log
import edu.mines.csci448.examples.samodelkin.util.CharacterGenerator

class SamodelkinRepo private constructor(context: Context?) {
    companion object {
        private const val LOG_TAG = "448.SamodelkinRepo"
        private var INSTANCE: SamodelkinRepo? = null

        /**
         * @param context
         */
        fun getInstance(context: Context? = null): SamodelkinRepo {
            var instance = INSTANCE
            if(instance == null) {
                instance = SamodelkinRepo(context)
                INSTANCE = instance
            }
            return instance
        }
    }

    val characters: List<SamodelkinCharacter>

    init {
        Log.d(LOG_TAG, "initializing repository list")
        val characterList = mutableListOf<SamodelkinCharacter>()
        for(i in 1..10) {
            characterList += CharacterGenerator.generateRandomCharacter(context)
        }
        characters = characterList.toList()
    }

}