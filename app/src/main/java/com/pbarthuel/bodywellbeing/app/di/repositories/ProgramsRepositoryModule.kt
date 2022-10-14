package com.pbarthuel.bodywellbeing.app.di.repositories

import com.pbarthuel.bodywellbeing.data.repositories.local.room.programs.RoomProgramsRepositoryImpl
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.programs.RoomProgramsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class ProgramsRepositoryModule {

    @Binds
    abstract fun bindRoomProgramsRepository(roomProgramsRepository: RoomProgramsRepositoryImpl): RoomProgramsRepository
}