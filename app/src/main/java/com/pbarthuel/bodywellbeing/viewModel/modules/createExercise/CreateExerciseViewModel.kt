package com.pbarthuel.bodywellbeing.viewModel.modules.createExercise

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pbarthuel.bodywellbeing.app.model.Exercise
import com.pbarthuel.bodywellbeing.domain.repositories.local.dataStore.PreferenceDataStoreRepository
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.exercises.RoomCustomExercisesRepository
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.user.RoomUserRepository
import com.pbarthuel.bodywellbeing.domain.repositories.network.ExerciseCloudFirestoreRepository
import com.pbarthuel.bodywellbeing.viewModel.utils.CoroutineToolsProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

sealed class CreateExerciseState {
    object DEFAULT : CreateExerciseState()
    object LOADING : CreateExerciseState()
    object SUCCESS : CreateExerciseState()
    data class FAIL(val error: Throwable) : CreateExerciseState()
}

@HiltViewModel
class CreateExerciseViewModel @Inject constructor(
    private val exerciseCloudFirestoreRepository: ExerciseCloudFirestoreRepository,
    private val customExercisesRepository: RoomCustomExercisesRepository,
    private val preferenceDataStoreRepository: PreferenceDataStoreRepository,
    private val roomUserRepository: RoomUserRepository,
    private val dispatcher: CoroutineToolsProvider
) : ViewModel() {

    var name: MutableState<String> = mutableStateOf("")
    var exerciseType: MutableState<Int?> = mutableStateOf(null)
    var imageUrl: MutableState<String> = mutableStateOf("")
    var description: MutableState<String> = mutableStateOf("")

    private val _state: MutableState<CreateExerciseState> = mutableStateOf(CreateExerciseState.DEFAULT)
    val state: State<CreateExerciseState> = _state

    fun isUserAdmin(): Flow<Boolean?> = roomUserRepository.isUserAdmin().flowOn(dispatcher.io)

    fun createExercise(isCreateClassicExercise: Boolean) {
        viewModelScope.launch(dispatcher.io) {
            if (isCreateClassicExercise) {
                exerciseCloudFirestoreRepository.createExercise(
                    Exercise.Classic(
                        id = UUID.randomUUID().toString(),
                        name = name.value,
                        type = exerciseType.value ?: throw Exception(""),
                        image = imageUrl.value,
                        description = description.value
                    )
                )
            } else {
                createCustomExercise()
            }
        }.invokeOnCompletion { throwable ->
            when (throwable) {
                null -> _state.value = CreateExerciseState.SUCCESS
                else -> _state.value = CreateExerciseState.FAIL(throwable)
            }
        }
    }

    private suspend fun createCustomExercise() {
        val exerciseId = UUID.randomUUID().toString()
        kotlin.runCatching {
            exerciseCloudFirestoreRepository.createCustomExercise(
                userId = preferenceDataStoreRepository.getUserId()
                    ?: throw Exception("userId shouldn't be null"),
                Exercise.Custom(
                    id = exerciseId,
                    name = name.value,
                    type = exerciseType.value ?: throw Exception(""),
                    image = imageUrl.value,
                    description = description.value
                )
            )
        }.onSuccess {
            customExercisesRepository.createExercise(
                Exercise.Custom(
                    id = exerciseId,
                    name = name.value,
                    type = exerciseType.value ?: throw Exception(""),
                    image = imageUrl.value,
                    description = description.value
                ),
                isSync = true
            )
        }.onFailure {
            customExercisesRepository.createExercise(
                Exercise.Custom(
                    id = exerciseId,
                    name = name.value,
                    type = exerciseType.value ?: throw Exception(""),
                    image = imageUrl.value,
                    description = description.value
                ),
                isSync = false
            )
        }
    }
}