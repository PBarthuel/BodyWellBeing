package com.pbarthuel.bodywellbeing.app.di.repositories

import com.pbarthuel.bodywellbeing.data.repositories.local.room.user.UserRepositoryImpl
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.user.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class UserRepositoryModule {

    @Binds
    abstract fun bindUserRepository(userRepository: UserRepositoryImpl): UserRepository
}