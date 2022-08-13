package com.pbarthuel.bodywellbeing.domain.repositories.network

import com.pbarthuel.bodywellbeing.app.models.Exercise
import kotlinx.coroutines.flow.Flow

interface ExerciseCloudFirestoreRepository {
    fun getAllExercises(): Flow<List<Exercise>>
    fun createExercise(exercise: Exercise)
    fun addExerciseToFavorite(userId: String, exercise: Exercise)
    fun getFavoriteExercises(userId: String): List<Exercise>
    fun deleteExerciseFromFavorite(userId: String, exerciseId: String)
    fun createCustomExercise(userId: String, exercise: Exercise)
    fun getCustomExercises(userId: String): List<Exercise>
}