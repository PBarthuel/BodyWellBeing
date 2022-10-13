package com.pbarthuel.bodywellbeing.domain.repositories.local.room.programs

import com.pbarthuel.bodywellbeing.app.model.program.ProgramOverview
import com.pbarthuel.bodywellbeing.app.model.program.ProgramPreview
import com.pbarthuel.bodywellbeing.data.model.program.WsProgram
import com.pbarthuel.bodywellbeing.data.vendors.local.room.programs.program.entities.ProgramEntity
import kotlinx.coroutines.flow.Flow

interface RoomProgramsRepository {
    fun getAllProgramsPreviews(): Flow<List<ProgramPreview>?>
    fun getProgramOverview(programId: String): Flow<ProgramOverview?>
    suspend fun createProgram(program: WsProgram)
}