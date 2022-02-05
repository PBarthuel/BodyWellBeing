package com.pbarthuel.bodywellbeing.domain.repositories.local.room.user

import com.pbarthuel.bodywellbeing.app.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUser(): Flow<User>
    suspend fun createUser(user: User)
    suspend fun updateUser(user: User)
    suspend fun clearUserDb()
}