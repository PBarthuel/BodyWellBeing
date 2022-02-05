package com.pbarthuel.bodywellbeing.data.repositories.local.dataStore

import com.pbarthuel.bodywellbeing.data.vendors.local.dataStore.PreferencesDataStoreDao
import com.pbarthuel.bodywellbeing.domain.repositories.local.dataStore.PreferenceDataStoreRepository
import com.pbarthuel.bodywellbeing.viewModel.modules.login.LoginState
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class PreferenceDataStoreRepositoryImpl @Inject constructor(
    private val preferencesDataStoreDao: PreferencesDataStoreDao
) : PreferenceDataStoreRepository {

    override suspend fun saveToDataStore(userId: String, isAlreadyCreated: Boolean) =
        preferencesDataStoreDao.saveToDataStore(userId = userId, isAlreadyCreated = isAlreadyCreated)

    override suspend fun getUserId(): String? =
        preferencesDataStoreDao.getUserId()

    override suspend fun clearDataStore() =
        preferencesDataStoreDao.clearDataStore()

    override fun isUserConnected(): Flow<LoginState?> =
        preferencesDataStoreDao.isUserConnected()
}