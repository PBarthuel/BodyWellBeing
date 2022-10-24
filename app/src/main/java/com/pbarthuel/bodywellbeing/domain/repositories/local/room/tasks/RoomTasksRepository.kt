package com.pbarthuel.bodywellbeing.domain.repositories.local.room.tasks

import com.pbarthuel.bodywellbeing.domain.model.program.Task
import kotlinx.coroutines.flow.Flow

interface RoomTasksRepository {
    fun getAllTasks(): Flow<List<Task>>
    suspend fun createTask(task: Task, dayIndex: Int)
    suspend fun clearTasksDb()
}