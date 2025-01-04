package com.jesse.c25a.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreEx @Inject constructor(private val dataStore: DataStore<Preferences>) {
    private object DataStoreKeys {
        const val DATA_STORE_KEY = "DATA_STORE_KEY"
        val nameKey = stringPreferencesKey(DATA_STORE_KEY)
    }

    suspend fun saveName(name: String) {
        dataStore.edit {
            it[DataStoreKeys.nameKey] = name
        }
    }
    val readName : Flow<String> = dataStore.data.map {
        it[DataStoreKeys.nameKey] ?: ""
    }
}