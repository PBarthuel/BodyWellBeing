package com.pbarthuel.bodywellbeing.app.di.repositories

import com.pbarthuel.bodywellbeing.data.repositories.network.ExerciseCloudFirestoreRepositoryImpl
import com.pbarthuel.bodywellbeing.domain.repositories.network.ExerciseCloudFirestoreRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Module
@InstallIn(ViewModelComponent::class)
abstract class CloudFirestoreRepositoryModule {

    @Binds
    abstract fun bindExerciseCloudFirestoreRepository(exerciseCloudFirestoreRepository: ExerciseCloudFirestoreRepositoryImpl): ExerciseCloudFirestoreRepository
}