package com.pbarthuel.bodywellbeing.data.repositories.local.room.exercises

import com.pbarthuel.bodywellbeing.app.model.CondenseExercise
import com.pbarthuel.bodywellbeing.app.model.Exercise
import com.pbarthuel.bodywellbeing.data.constants.ExercisesConstants
import com.pbarthuel.bodywellbeing.data.vendors.local.room.exercises.exercise.ExercisesDao
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.exercises.RoomExercisesRepository
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapLatest

@ExperimentalCoroutinesApi
class RoomExercisesRepositoryImpl @Inject constructor(
    private val exercisesDao: ExercisesDao
) : RoomExercisesRepository {

    override fun getAllExercises(): Flow<List<Exercise>> =
        exercisesDao.getAllExercises().mapLatest { exercisesEntity ->
            exercisesEntity?.map { it.toExercise() } ?: listOf()
        }

    override fun getExerciseFromId(exerciseId: String): Flow<Exercise> =
        exercisesDao.getExerciseFromId(exerciseId = exerciseId)?.mapLatest { exerciseEntity ->
            exerciseEntity.toExercise()
        } ?: flow {  }

    override fun getAllCondenseExercises(): Flow<List<CondenseExercise>> =
        exercisesDao.getAllCondenseExercises().mapLatest { condenseExercisesEntity ->
            condenseExercisesEntity?.map { it.toClassicCondenseExercise() } ?: listOf()
        }

    override fun getArmExercises(): Flow<List<CondenseExercise>> =
        exercisesDao.getCondenseExercisesFromType(ExercisesConstants.ARM_EXERCISE_TYPE)
            .mapLatest { condenseExercisesEntity ->
                condenseExercisesEntity?.map { it.toClassicCondenseExercise() } ?: listOf()
            }

    override fun getTricepsExercises(): Flow<List<CondenseExercise>> =
        exercisesDao.getCondenseExercisesFromType(ExercisesConstants.TRICEPS_EXERCISE_TYPE)
            .mapLatest { condenseExercisesEntity ->
                condenseExercisesEntity?.map { it.toClassicCondenseExercise() } ?: listOf()
            }

    override fun getBackExercises(): Flow<List<CondenseExercise>> =
        exercisesDao.getCondenseExercisesFromType(ExercisesConstants.BACK_EXERCISE_TYPE)
            .mapLatest { condenseExercisesEntity ->
                condenseExercisesEntity?.map { it.toClassicCondenseExercise() } ?: listOf()
            }

    override fun getShoulderExercises(): Flow<List<CondenseExercise>> =
        exercisesDao.getCondenseExercisesFromType(ExercisesConstants.SHOULDER_EXERCISE_TYPE)
            .mapLatest { condenseExercisesEntity ->
                condenseExercisesEntity?.map { it.toClassicCondenseExercise() } ?: listOf()
            }

    override fun getChestExercises(): Flow<List<CondenseExercise>> =
        exercisesDao.getCondenseExercisesFromType(ExercisesConstants.CHEST_EXERCISE_TYPE)
            .mapLatest { condenseExercisesEntity ->
                condenseExercisesEntity?.map { it.toClassicCondenseExercise() } ?: listOf()
            }

    override fun getAbsExercises(): Flow<List<CondenseExercise>> =
        exercisesDao.getCondenseExercisesFromType(ExercisesConstants.ABS_EXERCISE_TYPE)
            .mapLatest { condenseExercisesEntity ->
                condenseExercisesEntity?.map { it.toClassicCondenseExercise() } ?: listOf()
            }

    override fun getLegExercises(): Flow<List<CondenseExercise>> =
        exercisesDao.getCondenseExercisesFromType(ExercisesConstants.LEG_EXERCISE_TYPE)
            .mapLatest { condenseExercisesEntity ->
                condenseExercisesEntity?.map { it.toClassicCondenseExercise() } ?: listOf()
            }

    override fun getFavoritesExercises(): Flow<List<CondenseExercise>> =
        exercisesDao.getCondenseFavoritesExercises().mapLatest { condenseExercisesEntity ->
            condenseExercisesEntity?.map { it.toClassicCondenseExercise() } ?: listOf()
        }

    override suspend fun createExercise(exercise: Exercise) =
        exercisesDao.createExercise(exercise.toExerciseEntity())

    override suspend fun updateIsFavorite(exerciseId: String, isFavorite: Boolean) =
        exercisesDao.updateIsFavorite(
            exerciseId = exerciseId,
            isFavorite = isFavorite
        )

    override suspend fun resetAllIsFavoriteAtLogout() = exercisesDao.resetAllIsFavoriteAtLogout()

    override suspend fun clearExercisesDb() = exercisesDao.clearExercisesDb()
}