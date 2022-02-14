package com.pbarthuel.bodywellbeing.viewModel.modules.exerciseDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pbarthuel.bodywellbeing.app.models.Exercise
import com.pbarthuel.bodywellbeing.domain.repositories.local.CurrentExerciseIdRepository
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.exercises.ExercisesRepository
import com.pbarthuel.bodywellbeing.viewModel.utils.CoroutineToolsProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Exception
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch

@HiltViewModel
class ExerciseDetailViewModel @Inject constructor(
    private val dispatcher: CoroutineToolsProvider,
    private val currentExerciseIdRepository: CurrentExerciseIdRepository,
    private val exercisesRepository: ExercisesRepository
) : ViewModel() {

    val exercise: Flow<Exercise?> = exercisesRepository.getExerciseFromId(
        currentExerciseIdRepository.exerciseId.value ?: throw Exception("CurrentExerciseId is null")
    ).flowOn(dispatcher.io)

    fun modifyFavoriteState(exerciseId: String, isFavorite: Boolean) {
        viewModelScope.launch(dispatcher.io) {
            exercisesRepository.updateIsFavorite(
                exerciseId = exerciseId,
                isFavorite = !isFavorite
            )
        }
    }
}