package com.pbarthuel.bodywellbeing.domain.repositories.network

import com.pbarthuel.bodywellbeing.app.models.User

interface RealTimeDatabaseRepository {
    fun createUser(user: User)
    fun getUser(userId: String): User
}