package com.pbarthuel.bodywellbeing.domain.repositories.local

import com.pbarthuel.bodywellbeing.viewModel.modules.login.LoginState
import kotlinx.coroutines.flow.Flow

interface PreferenceDataStoreRepository {
    suspend fun saveToDataStore(userId: String, email: String, isAlreadyCreated: Boolean)
    suspend fun getUserId(): String?
    suspend fun getUserEmail(): String?
    suspend fun clearDataStore()
    fun isUserConnected(): Flow<LoginState?>
}