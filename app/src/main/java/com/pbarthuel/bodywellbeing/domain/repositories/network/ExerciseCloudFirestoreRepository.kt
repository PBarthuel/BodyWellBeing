package com.pbarthuel.bodywellbeing.domain.repositories.network

import com.pbarthuel.bodywellbeing.app.models.Exercise

interface ExerciseCloudFirestoreRepository {
    fun createExercise(exercise: Exercise)
    fun getExercise(exerciseId: String): Exercise
    fun addExerciseToFavorite(userId: String, exercise: Exercise)
    fun getFavoriteExercises(userId: String): List<Exercise>
    fun deleteExerciseFromFavorite(userId: String, exerciseId: String)
    fun createCustomExercise(userId: String, exercise: Exercise)
    fun getCustomExercises(userId: String): List<Exercise>
}