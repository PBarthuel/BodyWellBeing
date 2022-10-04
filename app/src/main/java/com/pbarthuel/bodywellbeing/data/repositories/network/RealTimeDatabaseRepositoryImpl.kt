package com.pbarthuel.bodywellbeing.data.repositories.network

import com.pbarthuel.bodywellbeing.app.model.User
import com.pbarthuel.bodywellbeing.data.vendors.network.RealTimeDatabaseDao
import com.pbarthuel.bodywellbeing.domain.repositories.network.RealTimeDatabaseRepository
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class RealTimeDatabaseRepositoryImpl @Inject constructor(
    private val realTimeDatabaseDao: RealTimeDatabaseDao
) : RealTimeDatabaseRepository {

    override suspend fun checkIfUserAlreadyExist(userId: String, email: String) =
        realTimeDatabaseDao.checkIfUserAlreadyExist(userId = userId, email = email)

    override suspend fun updateUserFromAccountCreation(user: User) =
        realTimeDatabaseDao.updateUserFromAccountCreation(user)
}