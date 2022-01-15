package com.pbarthuel.bodywellbeing.data.vendors.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map

class PreferencesDataStoreDao @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_DATASTORE)

    companion object {
        const val USER_DATASTORE = "USER_DATASTORE"

        val USER_ID = stringPreferencesKey("USER_ID")
    }

    suspend fun saveToDataStore(userId: String) {
        context.dataStore.edit {
            it[USER_ID] = userId
        }
    }

    fun getFromDataStore() = context.dataStore.data.map {
        it[USER_ID]
    }

    suspend fun clearDataStore() {
        context.dataStore.edit {
            it.clear()
        }
    }

    suspend fun isUserConnected(): Boolean {
        var uid: String? = null
        context.dataStore.edit {
            uid = it[USER_ID]
        }
        return uid != null
    }
}