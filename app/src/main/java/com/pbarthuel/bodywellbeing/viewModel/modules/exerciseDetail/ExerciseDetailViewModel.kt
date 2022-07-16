package com.pbarthuel.bodywellbeing.viewModel.modules.exerciseDetail

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pbarthuel.bodywellbeing.app.models.Exercise
import com.pbarthuel.bodywellbeing.app.modules.main.MainActivity.Companion.EXTRA_EXERCISE_ID
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.exercises.ExercisesRepository
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
    private val exercisesRepository: ExercisesRepository,
    private val dispatcher: CoroutineToolsProvider,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val exerciseId: String? = savedStateHandle.get<String>(EXTRA_EXERCISE_ID)

    val exercise: Flow<Exercise> = exerciseId?.let {
        exercisesRepository
        .getExerciseFromId(exerciseId = it).flowOn(dispatcher.io)
    } ?: flow { }

    fun modifyFavoriteState(exerciseId: String, isFavorite: Boolean) {
        viewModelScope.launch(dispatcher.io) {
            exercisesRepository.updateIsFavorite(
                exerciseId = exerciseId,
                isFavorite = !isFavorite
            )
        }
    }
}