package com.pbarthuel.bodywellbeing.data.vendors.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.pbarthuel.bodywellbeing.viewModel.modules.main.LoginState
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class PreferencesDataStoreDao @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_DATASTORE)

    companion object {
        const val USER_DATASTORE = "USER_DATASTORE"

        val USER_ID = stringPreferencesKey("USER_ID")
        val USER_EMAIL = stringPreferencesKey("USER_EMAIL")
        val USER_ALREADY_CREATED = booleanPreferencesKey("USER_ALREADY_CREATED")
    }

    suspend fun saveToDataStore(userId: String, email: String, isAlreadyCreated: Boolean) {
        context.dataStore.edit {
            it[USER_ID] = userId
            it[USER_EMAIL] = email
            it[USER_ALREADY_CREATED] = isAlreadyCreated
        }
    }

    suspend fun getUserId() = context.dataStore.data.map {
        it[USER_ID]
    }.first()

    suspend fun getUserEmail() = context.dataStore.data.map {
        it[USER_EMAIL]
    }.first()

    suspend fun clearDataStore() {
        context.dataStore.edit {
            it.clear()
        }
    }

    fun isUserConnected(): Flow<LoginState?> =
        context.dataStore.data.map {
            when {
                (it[USER_ID] != null && it[USER_ALREADY_CREATED] == true) -> LoginState.Login
                (it[USER_ID] != null && it[USER_ALREADY_CREATED] == false) -> LoginState.CreateAccount
                else -> null
            }
        }
}