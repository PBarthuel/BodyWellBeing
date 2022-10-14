package com.pbarthuel.bodywellbeing.viewModel.modules.home

import androidx.lifecycle.ViewModel
import com.pbarthuel.bodywellbeing.app.model.program.ProgramPreview
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.programs.RoomProgramsRepository
import com.pbarthuel.bodywellbeing.viewModel.utils.CoroutineToolsProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dispatcher: CoroutineToolsProvider,
    private val roomProgramsRepository: RoomProgramsRepository
): ViewModel() {

    val programsPreviews: Flow<List<ProgramPreview>?> =
        roomProgramsRepository.getAllProgramsPreviews().flowOn(dispatcher.io)
}