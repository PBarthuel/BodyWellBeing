package com.pbarthuel.bodywellbeing.data.repositories.network

import com.pbarthuel.bodywellbeing.app.models.User
import com.pbarthuel.bodywellbeing.data.vendors.network.RealTimeDatabaseDao
import com.pbarthuel.bodywellbeing.domain.repositories.network.RealTimeDatabaseRepository
import javax.inject.Inject

class RealTimeDatabaseRepositoryImpl @Inject constructor(
    private val realTimeDatabaseDao: RealTimeDatabaseDao
) : RealTimeDatabaseRepository {

    override fun createUser(user: User) =
        realTimeDatabaseDao.createUser(user)

    override fun getUser(userId: String): User =
        realTimeDatabaseDao.getUser(userId)
}