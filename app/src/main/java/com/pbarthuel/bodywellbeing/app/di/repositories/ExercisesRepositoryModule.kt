package com.pbarthuel.bodywellbeing.app.di.repositories

import com.pbarthuel.bodywellbeing.data.repositories.local.room.exercises.ExercisesRepositoryImpl
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.exercises.ExercisesRepository
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
    abstract fun bindExercisesRepository(exercisesRepository: ExercisesRepositoryImpl): ExercisesRepository
}