package com.pbarthuel.bodywellbeing.viewModel.modules.exercises

import androidx.lifecycle.ViewModel
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.exercises.ExercisesRepository
import com.pbarthuel.bodywellbeing.viewModel.utils.CoroutineToolsProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

@HiltViewModel
class ExercisesViewModel @Inject constructor(
    private val dispatcher: CoroutineToolsProvider,
    private val exercisesRepository: ExercisesRepository
): ViewModel() {

    val exercisesGroupByType = exercisesRepository.getAllCondenseExercises().map { exercises ->
        exercises.groupBy { it.type }
    }.flowOn(dispatcher.io)
}