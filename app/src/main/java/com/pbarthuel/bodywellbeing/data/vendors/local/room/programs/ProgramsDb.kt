package com.pbarthuel.bodywellbeing.data.vendors.local.room.programs

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pbarthuel.bodywellbeing.data.vendors.local.room.programs.program.ProgramsDao
import com.pbarthuel.bodywellbeing.data.vendors.local.room.programs.program.TasksDao
import com.pbarthuel.bodywellbeing.data.vendors.local.room.programs.program.entities.ProgramEntity
import com.pbarthuel.bodywellbeing.data.vendors.local.room.programs.program.entities.TaskEntity

@Database(
    version = 2,
    entities = [
        (ProgramEntity::class),
        (TaskEntity::class)
    ]
)
abstract class ProgramsDb : RoomDatabase() {
    abstract fun ProgramsDao(): ProgramsDao
    abstract fun TasksDao(): TasksDao
}