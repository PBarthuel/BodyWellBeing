package com.pbarthuel.bodywellbeing.app.di.repositories

import com.pbarthuel.bodywellbeing.data.repositories.local.room.exercises.RoomCustomExercisesRepositoryImpl
import com.pbarthuel.bodywellbeing.data.repositories.local.room.exercises.RoomExercisesRepositoryImpl
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.exercises.RoomCustomExercisesRepository
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.exercises.RoomExercisesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Module
@InstallIn(ViewModelComponent::class)
abstract class ExercisesRepositoryModule {

    @Binds
    abstract fun bindRoomExercisesRepository(roomExercisesRepository: RoomExercisesRepositoryImpl): RoomExercisesRepository

    @Binds
    abstract fun bindCustomRoomExercisesRepository(customRoomExercisesRepository: RoomCustomExercisesRepositoryImpl): RoomCustomExercisesRepository
}