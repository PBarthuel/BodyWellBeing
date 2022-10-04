package com.pbarthuel.bodywellbeing.domain.repositories.local.room.user

import com.pbarthuel.bodywellbeing.app.model.User
import kotlinx.coroutines.flow.Flow

interface RoomUserRepository {
    fun getUser(): Flow<User>
    suspend fun getUserSuspend(): User
    fun isUserAdmin(): Flow<Boolean>
    suspend fun createUser(user: User)
    suspend fun updateUser(user: User)
    suspend fun clearUserDb()
}