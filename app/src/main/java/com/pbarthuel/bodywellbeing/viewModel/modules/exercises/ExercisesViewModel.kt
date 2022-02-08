package com.pbarthuel.bodywellbeing.viewModel.modules.exercises

import androidx.lifecycle.ViewModel
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.exercises.ExercisesRepository
import com.pbarthuel.bodywellbeing.viewModel.utils.CoroutineToolsProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.flowOn

@HiltViewModel
class ExercisesViewModel @Inject constructor(
    private val dispatcher: CoroutineToolsProvider,
    private val exercisesRepository: ExercisesRepository
): ViewModel() {

    val armExercises = exercisesRepository.getArmExercises().flowOn(dispatcher.io)
    val tricepsExercises = exercisesRepository.getTricepsExercises().flowOn(dispatcher.io)
    val backExercises = exercisesRepository.getBackExercises().flowOn(dispatcher.io)
    val shoulderExercises = exercisesRepository.getShoulderExercises().flowOn(dispatcher.io)
    val chestExercises = exercisesRepository.getChestExercises().flowOn(dispatcher.io)
    val absExercises = exercisesRepository.getAbsExercises().flowOn(dispatcher.io)
    val legExercises = exercisesRepository.getLegExercises().flowOn(dispatcher.io)
}