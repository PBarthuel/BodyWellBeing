package com.pbarthuel.bodywellbeing.data.repositories.network

import com.pbarthuel.bodywellbeing.app.models.Exercise
import com.pbarthuel.bodywellbeing.data.vendors.network.ExerciseCloudFirestoreDao
import com.pbarthuel.bodywellbeing.data.vendors.network.UserExerciseCloudFirestoreDao
import com.pbarthuel.bodywellbeing.domain.repositories.network.ExerciseCloudFirestoreRepository
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
class ExerciseCloudFirestoreRepositoryImpl @Inject constructor(
    private val exerciseCloudFirestoreDao: ExerciseCloudFirestoreDao,
    private val userExerciseCloudFirestoreDao: UserExerciseCloudFirestoreDao
) : ExerciseCloudFirestoreRepository {

    override fun getAllExercises(): Flow<List<Exercise>> =
        exerciseCloudFirestoreDao.getAllExercises()

    override fun createExercise(exercise: Exercise) =
        exerciseCloudFirestoreDao.createExercise(exercise = exercise)

    override fun addExerciseToFavorite(userId: String, exercise: Exercise) =
        userExerciseCloudFirestoreDao.addExerciseToFavorite(userId = userId, exercise = exercise)

    override fun getAllFavoriteExercises(userId: String): Flow<List<Exercise>> =
        userExerciseCloudFirestoreDao.getAllFavoriteExercises(userId = userId)

    override fun deleteExerciseFromFavorite(userId: String, exerciseId: String) =
        userExerciseCloudFirestoreDao.deleteExerciseFromFavorite(userId = userId, exerciseId = exerciseId)

    override fun createCustomExercise(userId: String, exercise: Exercise) =
        userExerciseCloudFirestoreDao.createCustomExercise(userId = userId, exercise = exercise)

    override fun getAllCustomExercises(userId: String): Flow<List<Exercise>> =
        userExerciseCloudFirestoreDao.getAllCustomExercises(userId = userId)
}