package com.pbarthuel.bodywellbeing.data.repositories.local

import com.pbarthuel.bodywellbeing.data.vendors.local.PreferencesDataStoreDao
import com.pbarthuel.bodywellbeing.domain.repositories.local.PreferenceDataStoreRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class PreferenceDataStoreRepositoryImpl @Inject constructor(
    private val preferencesDataStoreDao: PreferencesDataStoreDao
) : PreferenceDataStoreRepository {

    override suspend fun saveToDataStore(userId: String) =
        preferencesDataStoreDao.saveToDataStore(userId = userId)

    override fun getFromDataStore(): Flow<String?> =
        preferencesDataStoreDao.getFromDataStore()

    override suspend fun clearDataStore() =
        preferencesDataStoreDao.clearDataStore()

    override suspend fun isUserConnected(): Boolean =
        preferencesDataStoreDao.isUserConnected()
}