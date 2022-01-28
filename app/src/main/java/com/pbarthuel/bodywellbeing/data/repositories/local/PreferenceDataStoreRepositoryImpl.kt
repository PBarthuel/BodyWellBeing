package com.pbarthuel.bodywellbeing.data.repositories.local

import com.pbarthuel.bodywellbeing.data.vendors.local.PreferencesDataStoreDao
import com.pbarthuel.bodywellbeing.domain.repositories.local.PreferenceDataStoreRepository
import com.pbarthuel.bodywellbeing.viewModel.modules.login.LoginState
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class PreferenceDataStoreRepositoryImpl @Inject constructor(
    private val preferencesDataStoreDao: PreferencesDataStoreDao
) : PreferenceDataStoreRepository {

    override suspend fun saveToDataStore(userId: String, email: String, isAlreadyCreated: Boolean) =
        preferencesDataStoreDao.saveToDataStore(userId = userId, email = email, isAlreadyCreated = isAlreadyCreated)

    override suspend fun getUserId(): String? =
        preferencesDataStoreDao.getUserId()

    override suspend fun getUserEmail(): String? =
        preferencesDataStoreDao.getUserEmail()

    override suspend fun clearDataStore() =
        preferencesDataStoreDao.clearDataStore()

    override fun isUserConnected(): Flow<LoginState?> =
        preferencesDataStoreDao.isUserConnected()
}