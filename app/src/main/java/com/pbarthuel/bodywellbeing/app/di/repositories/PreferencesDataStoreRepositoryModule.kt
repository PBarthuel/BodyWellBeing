package com.pbarthuel.bodywellbeing.app.di.repositories

import com.pbarthuel.bodywellbeing.data.repositories.local.PreferenceDataStoreRepositoryImpl
import com.pbarthuel.bodywellbeing.domain.repositories.local.PreferenceDataStoreRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferencesDataStoreRepositoryModule {

    @Binds
    abstract fun bindPreferencesDataStoreRepository(
        preferencesDataStoreRepository: PreferenceDataStoreRepositoryImpl
    ): PreferenceDataStoreRepository
}