package com.pbarthuel.bodywellbeing.viewModel.modules.exerciseDetail

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pbarthuel.bodywellbeing.app.models.Exercise
import com.pbarthuel.bodywellbeing.app.modules.main.MainActivity.Companion.EXTRA_EXERCISE_ID
import com.pbarthuel.bodywellbeing.domain.repositories.local.dataStore.PreferenceDataStoreRepository
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.exercises.RoomExercisesRepository
import com.pbarthuel.bodywellbeing.domain.repositories.network.ExerciseCloudFirestoreRepository
import com.pbarthuel.bodywellbeing.viewModel.utils.CoroutineToolsProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@HiltViewModel
class ExerciseDetailViewModel @Inject constructor(
    private val exercisesRepository: RoomExercisesRepository,
    private val exerciseCloudFirestoreRepository: ExerciseCloudFirestoreRepository,
    private val preferenceDataStoreRepository: PreferenceDataStoreRepository,
    private val dispatcher: CoroutineToolsProvider,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val exerciseId: String? = savedStateHandle.get<String>(EXTRA_EXERCISE_ID)
    private lateinit var userId: String

    init {
        viewModelScope.launch(dispatcher.io) {
            userId = preferenceDataStoreRepository.getUserId() ?: throw Exception("userId shouldn't be null")
        }
    }

    val exercise: Flow<Exercise> = exerciseId?.let {
        exercisesRepository
        .getExerciseFromId(exerciseId = it).flowOn(dispatcher.io)
    } ?: flow { }

    fun modifyFavoriteState(exercise: Exercise, isFavorite: Boolean) {
        viewModelScope.launch(dispatcher.io) {
            exercisesRepository.updateIsFavorite(
                exerciseId = exercise.id,
                isFavorite = !isFavorite
            )
            if (!isFavorite) {
                exerciseCloudFirestoreRepository.addExerciseToFavorite(
                    userId = userId,
                    exercise = exercise
                )
            } else {
                exerciseCloudFirestoreRepository.deleteExerciseFromFavorite(
                    userId = userId,
                    exerciseId = exercise.id
                )
            }
        }
    }
}