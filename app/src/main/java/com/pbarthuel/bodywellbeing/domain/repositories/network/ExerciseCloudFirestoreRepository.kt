package com.pbarthuel.bodywellbeing.domain.repositories.network

import com.pbarthuel.bodywellbeing.app.models.Exercise

interface ExerciseCloudFirestoreRepository {
    fun createExercise(exercise: Exercise)
    fun getExercise(exerciseId: String): Exercise
}