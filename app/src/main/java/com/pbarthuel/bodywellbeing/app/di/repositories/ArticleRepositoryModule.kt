package com.pbarthuel.bodywellbeing.app.di.repositories

import com.pbarthuel.bodywellbeing.data.repositories.local.room.articles.RoomArticlesRepositoryImpl
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.articles.RoomArticlesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class ArticleRepositoryModule {

    @Binds
    abstract fun bindRoomArticlesRepository(roomArticlesRepository: RoomArticlesRepositoryImpl): RoomArticlesRepository
}