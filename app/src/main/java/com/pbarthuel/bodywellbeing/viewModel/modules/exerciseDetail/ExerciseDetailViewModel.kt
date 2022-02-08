package com.pbarthuel.bodywellbeing.viewModel.modules.exerciseDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pbarthuel.bodywellbeing.app.models.Exercise
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.exercises.ExercisesRepository
import com.pbarthuel.bodywellbeing.viewModel.utils.CoroutineToolsProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class ExerciseDetailViewModel @Inject constructor(
    private val dispatcher: CoroutineToolsProvider,
    private val exercisesRepository: ExercisesRepository
): ViewModel() {

    var exercise: Exercise? = null

    fun getExerciseForId(exerciseId: String) {
        viewModelScope.launch(dispatcher.io) {
            exercise = exercisesRepository.getExerciseFromId(exerciseId)
        }
    }
}