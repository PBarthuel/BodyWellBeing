package com.pbarthuel.bodywellbeing.viewModel.modules.createExercise

import androidx.lifecycle.ViewModel
import com.pbarthuel.bodywellbeing.app.models.Exercise
import com.pbarthuel.bodywellbeing.data.constants.ExercisesConstants
import com.pbarthuel.bodywellbeing.domain.repositories.network.ExerciseCloudFirestoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateExerciseViewModel @Inject constructor(
    private val exerciseCloudFirestoreRepository: ExerciseCloudFirestoreRepository
) : ViewModel() {

    fun createExercise() {
        exerciseCloudFirestoreRepository.createExercise(
            Exercise(
                id = "developpéCouché1",
                name = "Developpé couché",
                description = "Developpé couché",
                type = ExercisesConstants.CHEST_EXERCISE_TYPE
            )
        )
    }

    fun getExercise(exerciseId: String): Exercise =
        exerciseCloudFirestoreRepository.getExercise(exerciseId = exerciseId)
}