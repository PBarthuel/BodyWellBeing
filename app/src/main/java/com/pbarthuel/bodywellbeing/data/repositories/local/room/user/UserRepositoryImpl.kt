package com.pbarthuel.bodywellbeing.data.repositories.local.room.user

import com.pbarthuel.bodywellbeing.app.models.User
import com.pbarthuel.bodywellbeing.data.vendors.local.room.user.UserDao
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.user.UserRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
): UserRepository {

    override fun getUser(): Flow<User> =
        userDao.getUser().map { it.toUser() }

    override suspend fun createUser(user: User) =
        userDao.createUser(user.toUserRequest())

    override suspend fun updateUser(user: User) =
        userDao.updateUser(user.toUserRequest())
}