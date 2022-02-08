package com.pbarthuel.bodywellbeing.data.repositories.local.room.exercises

import com.pbarthuel.bodywellbeing.app.models.Exercise
import com.pbarthuel.bodywellbeing.data.constants.ExercisesConstants
import com.pbarthuel.bodywellbeing.data.vendors.local.room.exercises.ExercisesDao
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.exercises.ExercisesRepository
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest

@ExperimentalCoroutinesApi
class ExercisesRepositoryImpl @Inject constructor(
    private val exercisesDao: ExercisesDao
): ExercisesRepository {

    override fun getExercises(): Flow<List<Exercise>> =
        exercisesDao.getExercises().mapLatest { exercisesRequest ->
            exercisesRequest?.map { it.toExercise() } ?: listOf()
        }

    override fun getArmExercises(): Flow<List<Exercise>> =
        exercisesDao.getExercisesFromType(ExercisesConstants.ARM_EXERCISE_TYPE).mapLatest { exercisesRequest ->
            exercisesRequest?.map { it.toExercise() } ?: listOf()
        }

    override fun getTricepsExercises(): Flow<List<Exercise>> =
        exercisesDao.getExercisesFromType(ExercisesConstants.TRICEPS_EXERCISE_TYPE).mapLatest { exercisesRequest ->
            exercisesRequest?.map { it.toExercise() } ?: listOf()
        }

    override fun getBackExercises(): Flow<List<Exercise>> =
        exercisesDao.getExercisesFromType(ExercisesConstants.BACK_EXERCISE_TYPE).mapLatest { exercisesRequest ->
            exercisesRequest?.map { it.toExercise() } ?: listOf()
        }

    override fun getShoulderExercises(): Flow<List<Exercise>> =
        exercisesDao.getExercisesFromType(ExercisesConstants.SHOULDER_EXERCISE_TYPE).mapLatest { exercisesRequest ->
            exercisesRequest?.map { it.toExercise() } ?: listOf()
        }

    override fun getChestExercises(): Flow<List<Exercise>> =
        exercisesDao.getExercisesFromType(ExercisesConstants.CHEST_EXERCISE_TYPE).mapLatest { exercisesRequest ->
            exercisesRequest?.map { it.toExercise() } ?: listOf()
        }

    override fun getAbsExercises(): Flow<List<Exercise>> =
        exercisesDao.getExercisesFromType(ExercisesConstants.ABS_EXERCISE_TYPE).mapLatest { exercisesRequest ->
            exercisesRequest?.map { it.toExercise() } ?: listOf()
        }

    override fun getLegExercises(): Flow<List<Exercise>> =
        exercisesDao.getExercisesFromType(ExercisesConstants.LEG_EXERCISE_TYPE).mapLatest { exercisesRequest ->
            exercisesRequest?.map { it.toExercise() } ?: listOf()
        }

    override fun getFavoritesExercises(): Flow<List<Exercise>> =
        exercisesDao.getFavoritesExercises().map { exercisesRequest ->
            exercisesRequest?.map { it.toExercise() } ?: listOf()
        }

    override suspend fun createExercise(exercise: Exercise) = exercisesDao.createExercise(exercise.toExerciseEntity())

    override suspend fun updateExercise(exercise: Exercise) = exercisesDao.updateExercise(exercise.toExerciseEntity())

    override suspend fun clearExercisesDb() = exercisesDao.clearExercisesDb()
}