package com.pbarthuel.bodywellbeing.data.vendors.local.room.user

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pbarthuel.bodywellbeing.data.vendors.local.room.user.objectRequest.UserRequest

@Database(entities = [(UserRequest::class)], version = 1)
abstract class UserDb: RoomDatabase() {
    abstract fun UserDao(): UserDao
}