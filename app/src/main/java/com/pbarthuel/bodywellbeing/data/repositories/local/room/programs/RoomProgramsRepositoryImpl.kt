package com.pbarthuel.bodywellbeing.data.repositories.local.room.programs

import com.pbarthuel.bodywellbeing.app.model.program.ProgramOverview
import com.pbarthuel.bodywellbeing.app.model.program.ProgramPreview
import com.pbarthuel.bodywellbeing.data.model.program.WsProgram
import com.pbarthuel.bodywellbeing.data.vendors.local.room.programs.program.ProgramsDao
import com.pbarthuel.bodywellbeing.data.vendors.local.room.programs.program.entities.ProgramEntity
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.programs.RoomProgramsRepository
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest

class RoomProgramsRepositoryImpl@Inject constructor(
    private val programsDao: ProgramsDao
) : RoomProgramsRepository {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getAllProgramsPreviews(): Flow<List<ProgramPreview>?> =
        programsDao.getAllPrograms().mapLatest { programs ->
            programs?.map { program -> program.toProgramPreview() }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getProgramOverview(programId: String): Flow<ProgramOverview?> =
        programsDao.getProgramFromId(programId = programId).mapLatest { program ->
            program?.toProgramOverview()
        }

    override suspend fun createProgram(program: WsProgram) {
        programsDao.createProgram(
            ProgramEntity(
                id = program.id,
                title = program.title,
                thumbnail = program.thumbnail,
                description = program.description
            )
        )
    }

    override suspend fun clearProgramsDb() =
        programsDao.clearProgramsDb()
}