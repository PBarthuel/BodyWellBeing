package com.pbarthuel.bodywellbeing.viewModel.modules.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.exercises.RoomExercisesRepository
import com.pbarthuel.bodywellbeing.domain.repositories.network.ExerciseCloudFirestoreRepository
import com.pbarthuel.bodywellbeing.viewModel.utils.CoroutineToolsProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

sealed class MainScreenState {
    object Home: MainScreenState()
    object Body: MainScreenState()
    object Exercises: MainScreenState()
    object Profile: MainScreenState()
}

@HiltViewModel
class MainViewModel @Inject constructor(
    private val exerciseCloudFirestoreRepository: ExerciseCloudFirestoreRepository,
    private val roomExercisesRepository: RoomExercisesRepository,
    private val dispatcher: CoroutineToolsProvider
) : ViewModel() {

    private val _screenState: MutableStateFlow<MainScreenState> = MutableStateFlow(MainScreenState.Home)
    val screenState: StateFlow<MainScreenState> = _screenState

    fun onScreenChanged(screenState: MainScreenState) {
        _screenState.value = screenState
    }

    fun syncExercise() {
        viewModelScope.launch(dispatcher.io) {
            exerciseCloudFirestoreRepository.getAllExercises().collect { exercises ->
                if (exercises.isNotEmpty()) {
                    exercises.forEach { exercise ->
                        roomExercisesRepository.createExercise(exercise)
                    }
                }
            }
        }
    }
}