package com.pbarthuel.bodywellbeing.domain.repositories.network

import com.pbarthuel.bodywellbeing.app.models.User
import kotlinx.coroutines.flow.Flow

interface RealTimeDatabaseRepository {
    suspend fun getUser(userId: String, email: String): User?
}