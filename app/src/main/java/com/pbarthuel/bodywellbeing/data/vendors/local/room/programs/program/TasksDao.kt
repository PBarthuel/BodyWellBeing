package com.pbarthuel.bodywellbeing.data.vendors.local.room.programs.program

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pbarthuel.bodywellbeing.data.vendors.local.room.programs.program.entities.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDao {

    @Query("SELECT * FROM TaskEntity")
    fun getAllTasks(): Flow<List<TaskEntity>?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createTask(taskEntity: TaskEntity)

    @Query("DELETE FROM TaskEntity")
    suspend fun clearTasksDb()
}