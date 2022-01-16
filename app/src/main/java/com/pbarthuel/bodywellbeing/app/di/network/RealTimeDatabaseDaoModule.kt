package com.pbarthuel.bodywellbeing.app.di.network

import com.pbarthuel.bodywellbeing.data.vendors.network.RealTimeDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class RealTimeDatabaseDaoModule {

    @Provides
    fun provideRealTimeDatabaseDao(): RealTimeDatabaseDao =
        RealTimeDatabaseDao()
}