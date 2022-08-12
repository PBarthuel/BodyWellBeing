package com.pbarthuel.bodywellbeing.data.repositories.network

import com.pbarthuel.bodywellbeing.app.models.Exercise
import com.pbarthuel.bodywellbeing.data.vendors.network.ExerciseCloudFirestoreDao
import com.pbarthuel.bodywellbeing.data.vendors.network.FavoriteExerciseCloudFirestoreDao
import com.pbarthuel.bodywellbeing.domain.repositories.network.ExerciseCloudFirestoreRepository
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class ExerciseCloudFirestoreRepositoryImpl @Inject constructor(
    private val exerciseCloudFirestoreDao: ExerciseCloudFirestoreDao,
    private val favoriteExerciseCloudFirestoreDao: FavoriteExerciseCloudFirestoreDao
) : ExerciseCloudFirestoreRepository {

    override fun createExercise(exercise: Exercise) =
        exerciseCloudFirestoreDao.createExercise(exercise = exercise)

    override fun addExerciseToFavorite(userId: String, exercise: Exercise) =
        favoriteExerciseCloudFirestoreDao.addExerciseToFavorite(userId = userId, exercise = exercise)

    override fun getExercise(exerciseId: String): Exercise =
        exerciseCloudFirestoreDao.getExercise(exerciseId)

    override fun getFavoriteExercises(userId: String): List<Exercise> =
        favoriteExerciseCloudFirestoreDao.getFavoriteExercisesId(userId = userId)


}