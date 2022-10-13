package com.pbarthuel.bodywellbeing.viewModel.modules.programOverview

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.pbarthuel.bodywellbeing.app.model.program.ProgramOverview
import com.pbarthuel.bodywellbeing.app.modules.main.MainActivity
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.programs.RoomProgramsRepository
import com.pbarthuel.bodywellbeing.viewModel.utils.CoroutineToolsProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class ProgramOverviewViewModel @Inject constructor(
    val dispatcher: CoroutineToolsProvider,
    private val roomProgramsRepository: RoomProgramsRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var programId: String

    init {
        programId = savedStateHandle.get<String>(MainActivity.EXTRA_PROGRAM_ID)
            ?: throw Exception("ExerciseId couldn't be null")

    }

    val programOverview: Flow<ProgramOverview?> = roomProgramsRepository.getProgramOverview(programId = programId)
}