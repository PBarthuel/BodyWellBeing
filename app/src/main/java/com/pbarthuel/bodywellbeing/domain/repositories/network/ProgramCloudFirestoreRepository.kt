package com.pbarthuel.bodywellbeing.domain.repositories.network

import com.pbarthuel.bodywellbeing.data.model.program.WsProgram
import kotlinx.coroutines.flow.Flow

interface ProgramCloudFirestoreRepository {
    fun getAllPrograms(): Flow<List<WsProgram>>
    fun createProgram(program: WsProgram)
}