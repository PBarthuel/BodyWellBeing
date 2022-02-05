package com.pbarthuel.bodywellbeing.domain.repositories.network

interface RealTimeDatabaseRepository {
    suspend fun checkIfUserAlreadyExist(userId: String, email: String)
}