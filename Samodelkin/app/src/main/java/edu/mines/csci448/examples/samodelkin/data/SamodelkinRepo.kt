package edu.mines.csci448.examples.samodelkin.data

import android.content.Context
import android.util.Log
import edu.mines.csci448.examples.samodelkin.data.database.SamodelkinDao
import edu.mines.csci448.examples.samodelkin.data.database.SamodelkinDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.*

class SamodelkinRepo
private constructor(
    private val samodelkinDao: SamodelkinDao,
    private val coroutineScope: CoroutineScope = GlobalScope
) {
    companion object {
        private const val LOG_TAG = "448.SamodelkinRepo"
        private var INSTANCE: SamodelkinRepo? = null

        /**
         * @param context
         */
        fun getInstance(context: Context): SamodelkinRepo {
            var instance = INSTANCE
            if (instance == null) {
                val database = SamodelkinDatabase.getInstance(context)
                instance = SamodelkinRepo(database.samodelkinDao)
                INSTANCE = instance
            }
            return instance
        }
    }

    init {
        Log.d(LOG_TAG, "initializing repository list")
    }

    fun getCharacters(): Flow<List<SamodelkinCharacter>> = samodelkinDao.getCharacters()
    suspend fun getCharacter(id: UUID): SamodelkinCharacter? = samodelkinDao.getCharacterById(id)
    fun addCharacter(samodelkinCharacter: SamodelkinCharacter) {
        coroutineScope.launch {
            samodelkinDao.addCharacter(samodelkinCharacter)
        }
    }

    fun deleteCharacter(samodelkinCharacter: SamodelkinCharacter) {
        coroutineScope.launch {
            samodelkinDao.deleteCrime(samodelkinCharacter)
        }
    }
}