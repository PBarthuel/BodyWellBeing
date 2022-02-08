package com.pbarthuel.bodywellbeing.data.repositories.local.room.exercises

import com.pbarthuel.bodywellbeing.app.models.Exercise
import com.pbarthuel.bodywellbeing.data.vendors.local.room.exercises.ExercisesDao
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.exercises.ExercisesRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ExercisesRepositoryImpl @Inject constructor(
    private val exercisesDao: ExercisesDao
): ExercisesRepository {

    override fun getExercises(): Flow<List<Exercise>> =
        exercisesDao.getExercises().map { exercisesRequest ->
            exercisesRequest?.map { it.toExercise() } ?: listOf()
        }

    override fun getFavoritesExercises(): Flow<List<Exercise>> =
        exercisesDao.getFavoritesExercises().map { exercisesRequest ->
            exercisesRequest?.map { it.toExercise() } ?: listOf()
        }

    override suspend fun createExercise(exercise: Exercise) =
        exercisesDao.createExercise(exercise.toExerciseEntity())

    override suspend fun updateExercise(exercise: Exercise) =
        exercisesDao.updateExercise(exercise.toExerciseEntity())

    override suspend fun clearExercisesDb() =
        exercisesDao.clearExercisesDb()
}