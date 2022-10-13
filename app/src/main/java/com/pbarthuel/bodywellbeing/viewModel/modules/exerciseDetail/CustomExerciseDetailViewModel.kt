package com.pbarthuel.bodywellbeing.viewModel.modules.exerciseDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pbarthuel.bodywellbeing.app.model.Exercise
import com.pbarthuel.bodywellbeing.app.modules.main.MainActivity.Companion.EXTRA_EXERCISE_ID
import com.pbarthuel.bodywellbeing.domain.repositories.local.dataStore.PreferenceDataStoreRepository
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.exercises.RoomCustomExercisesRepository
import com.pbarthuel.bodywellbeing.domain.repositories.network.ExerciseCloudFirestoreRepository
import com.pbarthuel.bodywellbeing.viewModel.utils.CoroutineToolsProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

@HiltViewModel
class CustomExerciseDetailViewModel @Inject constructor(
    private val roomCustomExercisesRepository: RoomCustomExercisesRepository,
    private val exerciseCloudFirestoreRepository: ExerciseCloudFirestoreRepository,
    private val preferenceDataStoreRepository: PreferenceDataStoreRepository,
    private val dispatcher: CoroutineToolsProvider,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var exerciseId: String
    private lateinit var userId: String

    init {
        exerciseId = savedStateHandle.get<String>(EXTRA_EXERCISE_ID)
            ?: throw Exception("ExerciseId couldn't be null")
        viewModelScope.launch(dispatcher.io) {
            userId = preferenceDataStoreRepository.getUserId()
                ?: throw Exception("userId shouldn't be null")
        }
    }

    val exercise: Flow<Exercise> =
        roomCustomExercisesRepository.getExerciseFromId(exerciseId = exerciseId)
            .flowOn(dispatcher.io)

    fun modifyFavoriteState(exercise: Exercise, isFavorite: Boolean) {
        viewModelScope.launch(dispatcher.io) {
            if (!isFavorite) {
                kotlin.runCatching {
                    exerciseCloudFirestoreRepository.addExerciseToFavorite(
                        userId = userId,
                        exercise = exercise
                    )
                }.onSuccess {
                    roomCustomExercisesRepository.updateIsFavorite(
                        exerciseId = exercise.id,
                        isFavorite = true
                    )
                }.onFailure {
                    //TODO ajouter un toast d'erreur pour dire a l'utilisateur qu'on ne peut pas ajouter de favoris hors ligne
                }
            } else {
                kotlin.runCatching {
                    exerciseCloudFirestoreRepository.deleteExerciseFromFavorite(
                        userId = userId,
                        exerciseId = exercise.id
                    )
                }.onSuccess {
                    roomCustomExercisesRepository.updateIsFavorite(
                        exerciseId = exercise.id,
                        isFavorite = false
                    )
                }.onFailure {
                    //TODO ajouter un toast d'erreur pour dire a l'utilisateur qu'on ne peut pas ajouter de favoris hors ligne
                }
            }
        }
    }
}