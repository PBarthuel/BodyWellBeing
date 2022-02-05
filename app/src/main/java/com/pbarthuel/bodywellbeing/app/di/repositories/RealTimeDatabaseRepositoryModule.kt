package com.pbarthuel.bodywellbeing.app.di.repositories

import com.pbarthuel.bodywellbeing.data.repositories.network.RealTimeDatabaseRepositoryImpl
import com.pbarthuel.bodywellbeing.domain.repositories.network.RealTimeDatabaseRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
@InstallIn(ViewModelComponent::class)
abstract class RealTimeDatabaseRepositoryModule {

    @ExperimentalCoroutinesApi
    @Binds
    abstract fun bindRealTimeDatabaseRepository(
        realTimeDatabaseRepository: RealTimeDatabaseRepositoryImpl
    ): RealTimeDatabaseRepository
}