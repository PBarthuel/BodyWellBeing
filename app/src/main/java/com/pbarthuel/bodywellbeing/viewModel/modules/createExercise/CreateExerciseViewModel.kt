package com.pbarthuel.bodywellbeing.viewModel.modules.createExercise

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pbarthuel.bodywellbeing.app.models.Exercise
import com.pbarthuel.bodywellbeing.data.constants.ExercisesConstants
import com.pbarthuel.bodywellbeing.domain.repositories.local.dataStore.PreferenceDataStoreRepository
import com.pbarthuel.bodywellbeing.domain.repositories.network.ExerciseCloudFirestoreRepository
import com.pbarthuel.bodywellbeing.viewModel.utils.CoroutineToolsProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

@HiltViewModel
class CreateExerciseViewModel @Inject constructor(
    private val exerciseCloudFirestoreRepository: ExerciseCloudFirestoreRepository,
    private val preferenceDataStoreRepository: PreferenceDataStoreRepository,
    private val dispatcher: CoroutineToolsProvider
) : ViewModel() {

    private lateinit var userId: String

    init {
        viewModelScope.launch(dispatcher.io) {
            userId = preferenceDataStoreRepository.getUserId() ?: throw Exception("userId shouldn't be null")
        }
    }

    fun createExercise() {
        exerciseCloudFirestoreRepository.createExercise(
            Exercise(
                id = "developpéCouché2",
                name = "Developpé couché incliné",
                description = "Developpé couché incliné",
                type = ExercisesConstants.CHEST_EXERCISE_TYPE
            )
        )
    }

    fun createCustomExercise() {
        exerciseCloudFirestoreRepository.createCustomExercise(
            userId = userId,
            Exercise(
                id = UUID.randomUUID().toString(),
                name = "Developpé couché incliné",
                description = "Developpé couché incliné",
                type = ExercisesConstants.CHEST_EXERCISE_TYPE
            )
        )
    }
}