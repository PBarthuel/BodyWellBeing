package com.pbarthuel.bodywellbeing.viewModel.modules.createExercise

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pbarthuel.bodywellbeing.app.models.Exercise
import com.pbarthuel.bodywellbeing.data.constants.ExercisesConstants
import com.pbarthuel.bodywellbeing.domain.repositories.local.dataStore.PreferenceDataStoreRepository
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.exercises.RoomCustomExercisesRepository
import com.pbarthuel.bodywellbeing.domain.repositories.network.ExerciseCloudFirestoreRepository
import com.pbarthuel.bodywellbeing.viewModel.utils.CoroutineToolsProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class CreateExerciseViewModel @Inject constructor(
    private val exerciseCloudFirestoreRepository: ExerciseCloudFirestoreRepository,
    private val customExercisesRepository: RoomCustomExercisesRepository,
    private val preferenceDataStoreRepository: PreferenceDataStoreRepository,
    private val dispatcher: CoroutineToolsProvider
) : ViewModel() {

    fun createExercise() {
        exerciseCloudFirestoreRepository.createExercise(
            Exercise.Classic(
                id = "developpéCouché3",
                name = "Developpé couché décliné",
                description = "Developpé couché décliné",
                type = ExercisesConstants.CHEST_EXERCISE_TYPE
            )
        )
    }

    fun createCustomExercise() {
        viewModelScope.launch(dispatcher.io) {
            kotlin.runCatching {
                exerciseCloudFirestoreRepository.createCustomExercise(
                    userId = preferenceDataStoreRepository.getUserId() ?: throw Exception("userId shouldn't be null"),
                    Exercise.Custom(
                        id = UUID.randomUUID().toString(),
                        name = "Developpé couché custom",
                        description = "Developpé couché custom",
                        type = ExercisesConstants.CHEST_EXERCISE_TYPE
                    )
                )
            }.onSuccess {
                customExercisesRepository.createExercise(
                    Exercise.Custom(
                        id = UUID.randomUUID().toString(),
                        name = "Developpé couché custom",
                        description = "Developpé couché custom",
                        type = ExercisesConstants.CHEST_EXERCISE_TYPE
                    ),
                    isSync = true
                )
            }.onFailure {
                customExercisesRepository.createExercise(
                    Exercise.Custom(
                        id = UUID.randomUUID().toString(),
                        name = "Developpé couché custom",
                        description = "Developpé couché custom",
                        type = ExercisesConstants.CHEST_EXERCISE_TYPE
                    ),
                    isSync = false
                )
            }
        }
    }
}