package com.pbarthuel.bodywellbeing.viewModel.modules.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pbarthuel.bodywellbeing.R
import com.pbarthuel.bodywellbeing.app.model.Exercise
import com.pbarthuel.bodywellbeing.data.model.program.WsProgram
import com.pbarthuel.bodywellbeing.data.vendors.local.room.programs.program.entities.ProgramEntity
import com.pbarthuel.bodywellbeing.domain.repositories.local.dataStore.PreferenceDataStoreRepository
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.exercises.RoomCustomExercisesRepository
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.exercises.RoomExercisesRepository
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.programs.RoomProgramsRepository
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.user.RoomUserRepository
import com.pbarthuel.bodywellbeing.domain.repositories.network.ExerciseCloudFirestoreRepository
import com.pbarthuel.bodywellbeing.domain.repositories.network.ProgramCloudFirestoreRepository
import com.pbarthuel.bodywellbeing.viewModel.utils.CoroutineToolsProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

sealed class MainScreenState {
    object Home : MainScreenState()
    object Body : MainScreenState()
    object Exercises : MainScreenState()
    object Profile : MainScreenState()
}

@HiltViewModel
class MainViewModel @Inject constructor(
    private val exerciseCloudFirestoreRepository: ExerciseCloudFirestoreRepository,
    private val programCloudFirestoreRepository: ProgramCloudFirestoreRepository,
    private val roomExercisesRepository: RoomExercisesRepository,
    private val roomCustomExercisesRepository: RoomCustomExercisesRepository,
    private val roomProgramsRepository: RoomProgramsRepository,
    private val roomUserRepository: RoomUserRepository,
    private val preferenceDataStoreRepository: PreferenceDataStoreRepository,
    private val dispatcher: CoroutineToolsProvider
) : ViewModel() {

    private val _screenState: MutableStateFlow<MainScreenState> =
        MutableStateFlow(MainScreenState.Home)
    val screenState: StateFlow<MainScreenState> = _screenState

    private var userId: String

    init {
        runBlocking {
            userId = preferenceDataStoreRepository.getUserId()
                ?: throw Exception("userId shouldn't be null")
        }
    }

    fun isUserAdmin(): Flow<Boolean?> = roomUserRepository.isUserAdmin().flowOn(dispatcher.io)

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
        viewModelScope.launch(dispatcher.io) {
            exerciseCloudFirestoreRepository.getAllCustomExercises(userId = userId)
                .collect { customExercises ->
                    if (customExercises.isNotEmpty()) {
                        customExercises.forEach { exercise ->
                            roomCustomExercisesRepository.createExercise(
                                exercise = exercise,
                                isSync = true
                            )
                        }
                    }
                }
        }
        // TODO a la première connection la synchro bu a voir pourquoi
        viewModelScope.launch(dispatcher.io) {
            exerciseCloudFirestoreRepository.getAllFavoriteExercises(userId = userId)
                .collect { favoriteExercises ->
                    if (favoriteExercises.isNotEmpty()) {
                        favoriteExercises.forEach { exercise ->
                            when (exercise) {
                                is Exercise.Classic -> roomExercisesRepository.updateIsFavorite(
                                    exerciseId = exercise.id,
                                    isFavorite = true
                                )
                                is Exercise.Custom -> roomCustomExercisesRepository.updateIsFavorite(
                                    exerciseId = exercise.id,
                                    isFavorite = true
                                )
                            }
                        }
                    }
                }
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    fun createProgram() {
        val program = Json.decodeFromString<WsProgram>("""{ "id": "1", "title": "First Program", "thumbnail": "https://saucisse.com", "description": "This is a description, This is a description, This is a description, This is a description !", "days": [ { "dayIndex": 1, "tasks": [ { "id": "274eb337-7fda-4c4d-9014-7cfa432fc1ee", "thumbnail": "https://saucisse.com", "type": "article", "title": "first task" }, { "id": "274eb337-7fda-4c4d-9014-7cfa432fc1ee", "thumbnail": "https://saucisse.com", "type": "exercises", "title": "second task" } ] }, { "dayIndex": 3, "tasks": [ { "id": "274eb337-7fda-4c4d-9014-7cfa432fc1ee", "thumbnail": "https://saucisse.com", "type": "article", "title": "first task" }, { "id": "274eb337-7fda-4c4d-9014-7cfa432fc1ee", "thumbnail": "https://saucisse.com", "type": "exercises", "title": "second task" } ] }, { "dayIndex": 5, "tasks": [ { "id": "274eb337-7fda-4c4d-9014-7cfa432fc1ee", "thumbnail": "https://saucisse.com", "type": "article", "title": "first task" }, { "id": "274eb337-7fda-4c4d-9014-7cfa432fc1ee", "thumbnail": "https://saucisse.com", "type": "exercises", "title": "second task" } ] }, { "dayIndex": 7, "tasks": [ { "id": "274eb337-7fda-4c4d-9014-7cfa432fc1ee", "thumbnail": "https://saucisse.com", "type": "article", "title": "first task" }, { "id": "274eb337-7fda-4c4d-9014-7cfa432fc1ee", "thumbnail": "https://saucisse.com", "type": "exercises", "title": "second task" } ] }, { "dayIndex": 9, "tasks": [ { "id": "274eb337-7fda-4c4d-9014-7cfa432fc1ee", "thumbnail": "https://saucisse.com", "type": "article", "title": "first task" }, { "id": "274eb337-7fda-4c4d-9014-7cfa432fc1ee", "thumbnail": "https://saucisse.com", "type": "exercises", "title": "second task" } ] }, { "dayIndex": 11, "tasks": [ { "id": "274eb337-7fda-4c4d-9014-7cfa432fc1ee", "thumbnail": "https://saucisse.com", "type": "article", "title": "first task" }, { "id": "274eb337-7fda-4c4d-9014-7cfa432fc1ee", "thumbnail": "https://saucisse.com", "type": "exercises", "title": "second task" } ] }, { "dayIndex": 13, "tasks": [ { "id": "274eb337-7fda-4c4d-9014-7cfa432fc1ee", "thumbnail": "https://saucisse.com", "type": "article", "title": "first task" }, { "id": "274eb337-7fda-4c4d-9014-7cfa432fc1ee", "thumbnail": "https://saucisse.com", "type": "exercises", "title": "second task" } ] } ] }""")
        programCloudFirestoreRepository.createProgram(program)
    }

    fun syncProgram() {
        viewModelScope.launch(dispatcher.io) {
            programCloudFirestoreRepository.getAllPrograms()
                .collect { programs ->
                    if (programs.isNotEmpty()) {
                        programs.forEach { program -> roomProgramsRepository.createProgram(program) }
                    }
                }
        }
    }
}