package com.pbarthuel.bodywellbeing.app.di.local.dataStore

import android.content.Context
import com.pbarthuel.bodywellbeing.data.vendors.local.dataStore.PreferencesDataStoreDao
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.user.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PreferencesDataStoreDaoModule {

    @Provides
    @Singleton
    fun providePreferenceDataStoreDao(@ApplicationContext appContext: Context): PreferencesDataStoreDao =
        PreferencesDataStoreDao(context = appContext)
}