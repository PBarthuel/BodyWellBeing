package com.pbarthuel.bodywellbeing.domain.repositories.local

import kotlinx.coroutines.flow.Flow

interface PreferenceDataStoreRepository {
    suspend fun saveToDataStore(userId: String)
    fun getFromDataStore(): Flow<String?>
    suspend fun clearDataStore()
    suspend fun isUserConnected(): Boolean
}