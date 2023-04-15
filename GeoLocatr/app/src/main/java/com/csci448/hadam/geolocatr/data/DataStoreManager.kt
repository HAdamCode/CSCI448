package com.csci448.hadam.geolocatr.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager (private val context: Context){
    companion object {
        private const val DATA_STORE_NAME = "Location_Data_Store"
        private val DATA_KEY_1 = stringPreferencesKey("data_key_1")
        private val DATA_KEY_2 = booleanPreferencesKey("data_key_2")
    }
    private val Context.dataStore: DataStore<Preferences>
            by preferencesDataStore(name = DATA_STORE_NAME)

    val dataFlow1: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[DATA_KEY_1] ?: ""
    }

    val dataFlow2: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[DATA_KEY_2] ?: false
    }

    suspend fun setData1(newValue: String) {
        context.dataStore.edit { preferences ->
            preferences[DATA_KEY_1] = newValue
        }
    }

    suspend fun setData2(newValue: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[DATA_KEY_2] = newValue
        }
    }
}