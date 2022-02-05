package com.pbarthuel.bodywellbeing.data.vendors.local.room.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.pbarthuel.bodywellbeing.data.vendors.local.room.user.objectRequest.UserRequest
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM UserRequest")
    fun getUser(): Flow<UserRequest?>

    @Insert
    suspend fun createUser(userRequest: UserRequest)

    @Update
    suspend fun updateUser(userRequest: UserRequest)

    @Query("DELETE FROM UserRequest")
    suspend fun clearUserDb()
}