package com.pbarthuel.bodywellbeing.domain.repositories.local.room.programs

import com.pbarthuel.bodywellbeing.app.model.program.ProgramOverview
import com.pbarthuel.bodywellbeing.app.model.program.ProgramPreview
import com.pbarthuel.bodywellbeing.data.model.program.WsProgramDetail
import kotlinx.coroutines.flow.Flow

interface RoomProgramsRepository {
    fun getAllProgramsPreviews(): Flow<List<ProgramPreview>?>
    fun getProgramOverview(programId: String): Flow<ProgramOverview?>
    suspend fun joinProgram(programId: String, startDate: Long)
    suspend fun isProgramJoined(): Boolean
    fun getJoinedProgramOverview(): Flow<ProgramOverview?>
    suspend fun createProgram(program: WsProgramDetail)
    suspend fun clearProgramsDb()
}