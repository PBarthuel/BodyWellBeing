package com.pbarthuel.bodywellbeing.data.repositories.local.room.exercises

import com.pbarthuel.bodywellbeing.data.model.program.WsProgram
import com.pbarthuel.bodywellbeing.data.vendors.local.room.programs.program.ProgramsDao
import com.pbarthuel.bodywellbeing.data.vendors.local.room.programs.program.TasksDao
import com.pbarthuel.bodywellbeing.data.vendors.local.room.programs.program.entities.ProgramEntity
import com.pbarthuel.bodywellbeing.data.vendors.local.room.programs.program.entities.TaskEntity
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.programs.RoomProgramsRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class RoomProgramsRepositoryImpl@Inject constructor(
    private val programsDao: ProgramsDao,
    private val tasksDao: TasksDao
) : RoomProgramsRepository {

    override fun getAllPrograms(): Flow<List<ProgramEntity>?> =
        programsDao.getAllPrograms()

    override suspend fun createProgram(program: WsProgram) {
        programsDao.createProgram(
            ProgramEntity(
                id = program.id,
                title = program.title,
                thumbnail = program.thumbnail,
                description = program.description
            )
        )
        program.days.forEach { wsDay ->
            wsDay.tasks.forEach { wsTask ->
                tasksDao.createTask(
                    TaskEntity(
                        localId = 0,
                        id = wsTask.id,
                        dayIndex = wsDay.dayIndex,
                        thumbnail = wsTask.thumbnail,
                        title = wsTask.title,
                        type = wsTask.type
                    )
                )
            }
        }
    }
}