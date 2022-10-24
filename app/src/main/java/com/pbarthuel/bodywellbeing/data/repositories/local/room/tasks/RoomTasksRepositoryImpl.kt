package com.pbarthuel.bodywellbeing.data.repositories.local.room.tasks

import com.pbarthuel.bodywellbeing.data.vendors.local.room.programs.program.TasksDao
import com.pbarthuel.bodywellbeing.data.vendors.local.room.programs.program.entities.TaskEntity
import com.pbarthuel.bodywellbeing.domain.model.program.Task
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.tasks.RoomTasksRepository
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest

class RoomTasksRepositoryImpl @Inject constructor(
    private val tasksDao: TasksDao
) : RoomTasksRepository {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getAllTasks(): Flow<List<Task>> =
        tasksDao.getAllTasks().mapLatest { tasks ->
            tasks.map { task -> task.toDomain(task.dayIndex) }
        }

    override suspend fun createTask(task: Task, dayIndex: Int) =
        tasksDao.createTask(
            TaskEntity(
                localId = 0,
                id = task.id,
                title = task.title,
                thumbnail = task.thumbnail,
                dayIndex = dayIndex,
                type = task.type
            )
        )

    override suspend fun clearTasksDb() =
        tasksDao.clearTasksDb()
}