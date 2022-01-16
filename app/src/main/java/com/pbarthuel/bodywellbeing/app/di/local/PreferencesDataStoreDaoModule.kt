package com.pbarthuel.bodywellbeing.app.di.local

import android.content.Context
import com.pbarthuel.bodywellbeing.data.vendors.local.PreferencesDataStoreDao
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