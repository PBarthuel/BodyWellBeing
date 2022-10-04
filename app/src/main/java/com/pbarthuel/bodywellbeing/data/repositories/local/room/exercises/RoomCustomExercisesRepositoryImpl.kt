package com.pbarthuel.bodywellbeing.data.repositories.local.room.exercises

import com.pbarthuel.bodywellbeing.app.model.CondenseExercise
import com.pbarthuel.bodywellbeing.app.model.Exercise
import com.pbarthuel.bodywellbeing.data.constants.ExercisesConstants
import com.pbarthuel.bodywellbeing.data.vendors.local.room.exercises.customExercise.CustomExerciseDao
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.exercises.RoomCustomExercisesRepository
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest

@ExperimentalCoroutinesApi
class RoomCustomExercisesRepositoryImpl @Inject constructor(
    private val customExerciseDao: CustomExerciseDao
) : RoomCustomExercisesRepository {

    override fun getAllExercises(): Flow<List<Exercise>> =
        customExerciseDao.getAllExercises().mapLatest { customExercisesEntity ->
            customExercisesEntity?.map { it.toExercise() } ?: listOf()
        }

    override fun getExerciseFromId(exerciseId: String): Flow<Exercise> =
        customExerciseDao.getExerciseFromId(exerciseId = exerciseId).mapLatest { customExercisesEntity ->
            customExercisesEntity?.toExercise() ?: throw Exception("ExerciseId reference nothing")
        }

    override fun getAllCondenseExercises(): Flow<List<CondenseExercise>> =
        customExerciseDao.getAllCondenseExercises().mapLatest { customExercisesEntity ->
            customExercisesEntity?.map { it.toCustomCondenseExercise() } ?: listOf()
        }

    override fun getArmExercises(): Flow<List<CondenseExercise>> =
        customExerciseDao.getCondenseExercisesFromType(ExercisesConstants.ARM_EXERCISE_TYPE)
            .mapLatest { condenseExercisesEntity ->
                condenseExercisesEntity?.map { it.toCustomCondenseExercise() } ?: listOf()
            }

    override fun getTricepsExercises(): Flow<List<CondenseExercise>> =
        customExerciseDao.getCondenseExercisesFromType(ExercisesConstants.TRICEPS_EXERCISE_TYPE)
            .mapLatest { condenseExercisesEntity ->
                condenseExercisesEntity?.map { it.toCustomCondenseExercise() } ?: listOf()
            }

    override fun getBackExercises(): Flow<List<CondenseExercise>> =
        customExerciseDao.getCondenseExercisesFromType(ExercisesConstants.BACK_EXERCISE_TYPE)
            .mapLatest { condenseExercisesEntity ->
                condenseExercisesEntity?.map { it.toCustomCondenseExercise() } ?: listOf()
            }

    override fun getShoulderExercises(): Flow<List<CondenseExercise>> =
        customExerciseDao.getCondenseExercisesFromType(ExercisesConstants.SHOULDER_EXERCISE_TYPE)
            .mapLatest { condenseExercisesEntity ->
                condenseExercisesEntity?.map { it.toCustomCondenseExercise() } ?: listOf()
            }

    override fun getChestExercises(): Flow<List<CondenseExercise>> =
        customExerciseDao.getCondenseExercisesFromType(ExercisesConstants.CHEST_EXERCISE_TYPE)
            .mapLatest { condenseExercisesEntity ->
                condenseExercisesEntity?.map { it.toCustomCondenseExercise() } ?: listOf()
            }

    override fun getAbsExercises(): Flow<List<CondenseExercise>> =
        customExerciseDao.getCondenseExercisesFromType(ExercisesConstants.ABS_EXERCISE_TYPE)
            .mapLatest { condenseExercisesEntity ->
                condenseExercisesEntity?.map { it.toCustomCondenseExercise() } ?: listOf()
            }

    override fun getLegExercises(): Flow<List<CondenseExercise>> =
        customExerciseDao.getCondenseExercisesFromType(ExercisesConstants.LEG_EXERCISE_TYPE)
            .mapLatest { condenseExercisesEntity ->
                condenseExercisesEntity?.map { it.toCustomCondenseExercise() } ?: listOf()
            }

    override fun getFavoritesExercises(): Flow<List<CondenseExercise>> =
        customExerciseDao.getCondenseFavoritesExercises().mapLatest { condenseExercises ->
            condenseExercises?.map { it.toCustomCondenseExercise() } ?: listOf()
        }

    override suspend fun createExercise(exercise: Exercise, isSync: Boolean) =
        customExerciseDao.createExercise(exercise.toCustomExerciseEntity(isSync = isSync))

    override suspend fun updateIsFavorite(exerciseId: String, isFavorite: Boolean) =
        customExerciseDao.updateIsFavorite(exerciseId = exerciseId, isFavorite = isFavorite)

    override suspend fun clearExercisesDb() =
        customExerciseDao.clearExercisesDb()
}