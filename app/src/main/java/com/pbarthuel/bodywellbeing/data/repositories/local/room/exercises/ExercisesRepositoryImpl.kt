package com.pbarthuel.bodywellbeing.data.repositories.local.room.exercises

import com.pbarthuel.bodywellbeing.app.models.CondenseExercise
import com.pbarthuel.bodywellbeing.app.models.Exercise
import com.pbarthuel.bodywellbeing.data.constants.ExercisesConstants
import com.pbarthuel.bodywellbeing.data.vendors.local.room.exercises.ExercisesDao
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.exercises.ExercisesRepository
import java.lang.Exception
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest

@ExperimentalCoroutinesApi
class ExercisesRepositoryImpl @Inject constructor(
    private val exercisesDao: ExercisesDao
): ExercisesRepository {

    override fun getAllExercises(): Flow<List<Exercise>> = exercisesDao.getAllExercises().mapLatest { exercisesRequest ->
            exercisesRequest?.map { it.toExercise() } ?: listOf()
        }

    override fun getExerciseFromId(exerciseId: String): Flow<Exercise> =
        exercisesDao.getExerciseFromId(exerciseId = exerciseId).mapLatest { it?.toExercise() ?: throw Exception("ExerciseId reference nothing") }

    override fun getAllCondenseExercises(): Flow<List<CondenseExercise>> =
        exercisesDao.getAllCondenseExercises().map { exercises ->
            exercises?.map { it.toCondenseExercise() } ?: listOf()
        }

    override fun getArmExercises(): Flow<List<CondenseExercise>> =
        exercisesDao.getCondenseExercisesFromType(ExercisesConstants.ARM_EXERCISE_TYPE).mapLatest { exercisesRequest ->
            exercisesRequest?.map { it.toCondenseExercise() } ?: listOf()
        }

    override fun getTricepsExercises(): Flow<List<CondenseExercise>> =
        exercisesDao.getCondenseExercisesFromType(ExercisesConstants.TRICEPS_EXERCISE_TYPE).mapLatest { exercisesRequest ->
            exercisesRequest?.map { it.toCondenseExercise() } ?: listOf()
        }

    override fun getBackExercises(): Flow<List<CondenseExercise>> =
        exercisesDao.getCondenseExercisesFromType(ExercisesConstants.BACK_EXERCISE_TYPE).mapLatest { exercisesRequest ->
            exercisesRequest?.map { it.toCondenseExercise() } ?: listOf()
        }

    override fun getShoulderExercises(): Flow<List<CondenseExercise>> =
        exercisesDao.getCondenseExercisesFromType(ExercisesConstants.SHOULDER_EXERCISE_TYPE).mapLatest { exercisesRequest ->
            exercisesRequest?.map { it.toCondenseExercise() } ?: listOf()
        }

    override fun getChestExercises(): Flow<List<CondenseExercise>> =
        exercisesDao.getCondenseExercisesFromType(ExercisesConstants.CHEST_EXERCISE_TYPE).mapLatest { exercisesRequest ->
            exercisesRequest?.map { it.toCondenseExercise() } ?: listOf()
        }

    override fun getAbsExercises(): Flow<List<CondenseExercise>> =
        exercisesDao.getCondenseExercisesFromType(ExercisesConstants.ABS_EXERCISE_TYPE).mapLatest { exercisesRequest ->
            exercisesRequest?.map { it.toCondenseExercise() } ?: listOf()
        }

    override fun getLegExercises(): Flow<List<CondenseExercise>> =
        exercisesDao.getCondenseExercisesFromType(ExercisesConstants.LEG_EXERCISE_TYPE).mapLatest { exercisesRequest ->
            exercisesRequest?.map { it.toCondenseExercise() } ?: listOf()
        }

    override fun getFavoritesExercises(): Flow<List<CondenseExercise>> =
        exercisesDao.getCondenseFavoritesExercises().map { exercisesRequest ->
            exercisesRequest?.map { it.toCondenseExercise() } ?: listOf()
        }

    override suspend fun createExercise(exercise: Exercise) = exercisesDao.createExercise(exercise.toExerciseEntity())

    override suspend fun updateExercise(exercise: Exercise) = exercisesDao.updateExercise(exercise.toExerciseEntity())

    override suspend fun updateIsFavorite(exerciseId: String, isFavorite: Boolean) = exercisesDao.updateIsFavorite(
        exerciseId = exerciseId,
        isFavorite = isFavorite
    )

    override suspend fun clearExercisesDb() = exercisesDao.clearExercisesDb()
}