package com.pbarthuel.bodywellbeing.viewModel.modules.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pbarthuel.bodywellbeing.app.model.program.ProgramPreview
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.programs.RoomProgramsRepository
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.tasks.RoomTasksRepository
import com.pbarthuel.bodywellbeing.viewModel.utils.CoroutineToolsProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val roomProgramsRepository: RoomProgramsRepository,
    private val roomTasksRepository: RoomTasksRepository,
    private val dispatcher: CoroutineToolsProvider
): ViewModel() {

    val programsPreviews: Flow<List<ProgramPreview>?> =
        roomProgramsRepository.getAllProgramsPreviews().flowOn(dispatcher.io)

    val programTasks = roomTasksRepository.getAllTasks()
}