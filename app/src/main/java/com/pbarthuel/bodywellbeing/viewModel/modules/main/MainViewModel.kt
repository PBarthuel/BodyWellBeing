package com.pbarthuel.bodywellbeing.viewModel.modules.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pbarthuel.bodywellbeing.app.models.Exercise
import com.pbarthuel.bodywellbeing.data.constants.ExercisesConstants
import com.pbarthuel.bodywellbeing.domain.repositories.local.dataStore.PreferenceDataStoreRepository
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.exercises.ExercisesRepository
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.user.UserRepository
import com.pbarthuel.bodywellbeing.viewModel.utils.CoroutineToolsProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

sealed class MainScreenState {
    object Home: MainScreenState()
    object Body: MainScreenState()
    object Exercises: MainScreenState()
    object Profile: MainScreenState()
    object Logout: MainScreenState()
}

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dispatcher: CoroutineToolsProvider,
    private val preferenceDataStoreRepository: PreferenceDataStoreRepository,
    private val userRepository: UserRepository,
    private val exercisesRepository: ExercisesRepository
) : ViewModel() {

    private val _screenState: MutableStateFlow<MainScreenState> = MutableStateFlow(MainScreenState.Home)
    val screenState: StateFlow<MainScreenState> = _screenState

    fun onScreenChanged(screenState: MainScreenState) {
        _screenState.value = screenState
    }

    fun logOut() {
        viewModelScope.launch(dispatcher.io) {
            kotlin.runCatching {
                preferenceDataStoreRepository.clearDataStore()
                userRepository.clearUserDb()
            }.onSuccess {
                _screenState.value = MainScreenState.Logout
            }
        }
    }





    // TODO retirer ce code quand je créerai les exercises sur le backend
    // region A delete
    fun createExercise() {
        viewModelScope.launch(dispatcher.io) {
            if (exercisesRepository.getExercises().first().isEmpty()) {
                exercisesList().forEach {
                    exercisesRepository.createExercise(it)
                }
            }
        }
    }

    private fun exercisesList(): List<Exercise> {
        return listOf(
            Exercise(
                id = "developpéCouché1",
                name = "Developpé couché",
                description = "Developpé couché",
                type = ExercisesConstants.CHEST_EXERCISE_TYPE
            ),
            Exercise(
                id = "press1",
                name = "Press",
                description = "Press",
                type = ExercisesConstants.LEG_EXERCISE_TYPE
            ),
            Exercise(
                id = "curl1",
                name = "Curl",
                description = "Curl",
                type = ExercisesConstants.ARM_EXERCISE_TYPE
            ),
            Exercise(
                id = "dips1",
                name = "Dips",
                description = "Dips",
                type = ExercisesConstants.TRICEPS_EXERCISE_TYPE
            )
        )
    }
    //endregion
}