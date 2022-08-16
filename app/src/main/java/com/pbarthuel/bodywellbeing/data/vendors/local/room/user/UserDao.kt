package com.pbarthuel.bodywellbeing.data.vendors.local.room.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.pbarthuel.bodywellbeing.data.vendors.local.room.user.entities.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM UserEntity")
    fun getUser(): Flow<UserEntity>

    @Query("SELECT * FROM UserEntity")
    suspend fun getUserSuspend(): UserEntity

    @Insert
    suspend fun createUser(userEntity: UserEntity)

    @Update
    suspend fun updateUser(userEntity: UserEntity)

    @Query("DELETE FROM UserEntity")
    suspend fun clearUserDb()
}