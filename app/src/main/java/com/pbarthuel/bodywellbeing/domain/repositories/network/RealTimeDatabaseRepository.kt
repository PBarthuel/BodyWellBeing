package com.pbarthuel.bodywellbeing.domain.repositories.network

import com.pbarthuel.bodywellbeing.app.models.User

interface RealTimeDatabaseRepository {
    suspend fun checkIfUserAlreadyExist(userId: String, email: String)
    suspend fun updateUserFromAccountCreation(user: User)
}