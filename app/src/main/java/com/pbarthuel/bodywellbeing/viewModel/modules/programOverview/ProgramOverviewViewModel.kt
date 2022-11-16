package com.pbarthuel.bodywellbeing.viewModel.modules.programOverview

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pbarthuel.bodywellbeing.app.model.program.ProgramOverview
import com.pbarthuel.bodywellbeing.app.modules.main.MainActivity
import com.pbarthuel.bodywellbeing.domain.repositories.local.dataStore.PreferenceDataStoreRepository
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.programs.RoomProgramsRepository
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.tasks.RoomTasksRepository
import com.pbarthuel.bodywellbeing.domain.repositories.network.ProgramCloudFirestoreRepository
import com.pbarthuel.bodywellbeing.viewModel.utils.CoroutineToolsProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@HiltViewModel
class ProgramOverviewViewModel @Inject constructor(
    val dispatcher: CoroutineToolsProvider,
    private val roomProgramsRepository: RoomProgramsRepository,
    private val programCloudFirestoreRepository: ProgramCloudFirestoreRepository,
    private val preferenceDataStoreRepository: PreferenceDataStoreRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var programId: String
    private var userId: String

    init {
        runBlocking {
            userId = preferenceDataStoreRepository.getUserId()
                ?: throw Exception("userId shouldn't be null")
        }
        programId = savedStateHandle.get<String>(MainActivity.EXTRA_PROGRAM_ID)
            ?: throw Exception("ExerciseId couldn't be null")

    }

    val programOverview: Flow<ProgramOverview?> = roomProgramsRepository.getProgramOverview(programId = programId)

    fun joinProgram() {
        viewModelScope.launch(dispatcher.io) {
            val program = programCloudFirestoreRepository.getProgramById(programId = programId).first()
            programCloudFirestoreRepository.joinProgram(
                userId = userId,
                program = program
            )
        }
    }
}