package com.pbarthuel.bodywellbeing.app.di.repositories

import com.pbarthuel.bodywellbeing.data.repositories.local.room.user.RoomUserRepositoryImpl
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.user.RoomUserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class UserRepositoryModule {

    @Binds
    abstract fun bindRoomUserRepository(roomUserRepository: RoomUserRepositoryImpl): RoomUserRepository
}