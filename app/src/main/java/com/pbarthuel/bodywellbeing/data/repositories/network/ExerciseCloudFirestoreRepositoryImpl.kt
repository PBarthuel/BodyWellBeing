package com.pbarthuel.bodywellbeing.data.repositories.network

import com.pbarthuel.bodywellbeing.app.models.Exercise
import com.pbarthuel.bodywellbeing.data.vendors.network.ExerciseCloudFirestoreDao
import com.pbarthuel.bodywellbeing.domain.repositories.network.ExerciseCloudFirestoreRepository
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class ExerciseCloudFirestoreRepositoryImpl @Inject constructor(
    private val cloudFirestoreDao: ExerciseCloudFirestoreDao
) : ExerciseCloudFirestoreRepository {

    override fun createExercise(exercise: Exercise) {
        cloudFirestoreDao.createExercise(exercise = exercise)
    }

    override fun getExercise(exerciseId: String): Exercise =
        cloudFirestoreDao.getExercise(exerciseId)
}