package com.pbarthuel.bodywellbeing.domain.repositories.network

import com.pbarthuel.bodywellbeing.data.model.program.WsProgramDetail
import com.pbarthuel.bodywellbeing.domain.model.program.ProgramDetail
import kotlinx.coroutines.flow.Flow

interface ProgramCloudFirestoreRepository {
    fun getAllPrograms(): Flow<List<WsProgramDetail>>
    fun getProgramById(programId: String): Flow<ProgramDetail>
    fun createProgram(program: WsProgramDetail)
    fun joinProgram(userId: String, program: ProgramDetail)
    fun getJoinedProgram(userId: String): Flow<ProgramDetail?>
    fun leaveProgram(userId: String)
}