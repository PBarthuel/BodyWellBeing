package com.pbarthuel.bodywellbeing.app.di.local.room

import android.content.Context
import androidx.room.Room
import com.pbarthuel.bodywellbeing.data.vendors.local.room.exercises.ExercisesDao
import com.pbarthuel.bodywellbeing.data.vendors.local.room.exercises.ExercisesDb
import com.pbarthuel.bodywellbeing.data.vendors.local.room.user.UserDao
import com.pbarthuel.bodywellbeing.data.vendors.local.room.user.UserDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun provideUserDatabase(@ApplicationContext appContext: Context): UserDb {
        return Room.databaseBuilder(appContext, UserDb::class.java, "User")
            .build()
    }

    @Provides
    fun provideUserDao(userDb: UserDb): UserDao {
        return userDb.UserDao()
    }

    @Provides
    @Singleton
    fun provideExercisesDatabase(@ApplicationContext appContext: Context): ExercisesDb {
        return Room.databaseBuilder(appContext, ExercisesDb::class.java, "Exercises")
            .build()
    }

    @Provides
    fun provideExercisesDao(exercisesDb: ExercisesDb): ExercisesDao {
        return exercisesDb.ExercisesDao()
    }
}