package com.pbarthuel.bodywellbeing.data.repositories.network

import com.pbarthuel.bodywellbeing.app.models.User
import com.pbarthuel.bodywellbeing.data.vendors.network.RealTimeDatabaseDao
import com.pbarthuel.bodywellbeing.domain.repositories.network.RealTimeDatabaseRepository
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
class RealTimeDatabaseRepositoryImpl @Inject constructor(
    private val realTimeDatabaseDao: RealTimeDatabaseDao
) : RealTimeDatabaseRepository {

    override suspend fun getUser(userId: String, email: String): User? =
        realTimeDatabaseDao.getUser(userId = userId, email = email)
}