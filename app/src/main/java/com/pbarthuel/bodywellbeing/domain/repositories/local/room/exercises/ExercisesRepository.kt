package com.pbarthuel.bodywellbeing.domain.repositories.local.room.exercises

import com.pbarthuel.bodywellbeing.app.models.Exercise
import com.pbarthuel.bodywellbeing.app.models.User
import kotlinx.coroutines.flow.Flow

interface ExercisesRepository {
    fun getExercises(): Flow<List<Exercise>>
    suspend fun createExercise(exercise: Exercise)
    suspend fun updateExercise(exercise: Exercise)
    suspend fun clearExercisesDb()
}