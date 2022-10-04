package com.pbarthuel.bodywellbeing.domain.repositories.local.room.programs

import com.pbarthuel.bodywellbeing.data.model.program.WsProgram
import com.pbarthuel.bodywellbeing.data.vendors.local.room.programs.program.entities.ProgramEntity
import kotlinx.coroutines.flow.Flow

interface RoomProgramsRepository {
    fun getAllPrograms(): Flow<List<ProgramEntity>?>
    suspend fun createProgram(program: WsProgram)
}