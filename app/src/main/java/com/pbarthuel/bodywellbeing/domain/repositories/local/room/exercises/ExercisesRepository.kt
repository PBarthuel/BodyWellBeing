package com.pbarthuel.bodywellbeing.domain.repositories.local.room.exercises

import com.pbarthuel.bodywellbeing.app.models.Exercise
import com.pbarthuel.bodywellbeing.app.models.User
import kotlinx.coroutines.flow.Flow

interface ExercisesRepository {
    fun getExercises(): Flow<List<Exercise>>
    fun getArmExercises(): Flow<List<Exercise>>
    fun getTricepsExercises(): Flow<List<Exercise>>
    fun getBackExercises(): Flow<List<Exercise>>
    fun getShoulderExercises(): Flow<List<Exercise>>
    fun getChestExercises(): Flow<List<Exercise>>
    fun getAbsExercises(): Flow<List<Exercise>>
    fun getLegExercises(): Flow<List<Exercise>>
    fun getFavoritesExercises(): Flow<List<Exercise>>
    suspend fun createExercise(exercise: Exercise)
    suspend fun updateExercise(exercise: Exercise)
    suspend fun clearExercisesDb()
}