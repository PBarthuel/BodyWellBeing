package com.pbarthuel.bodywellbeing.app.di.repositories

import com.pbarthuel.bodywellbeing.data.repositories.local.room.tasks.RoomTasksRepositoryImpl
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.tasks.RoomTasksRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class TasksRepositoryModule {

    @Binds
    abstract fun bindRoomTasksRepository(roomTasksRepository: RoomTasksRepositoryImpl): RoomTasksRepository
}