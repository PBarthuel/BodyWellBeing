package com.pbarthuel.bodywellbeing.data.vendors.local.room.programs

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pbarthuel.bodywellbeing.data.vendors.local.room.programs.program.ProgramsDao
import com.pbarthuel.bodywellbeing.data.vendors.local.room.programs.program.entities.ProgramEntity

@Database(
    version = 1,
    entities = [(ProgramEntity::class)]
)
abstract class ProgramsDb : RoomDatabase() {
    abstract fun ProgramsDao(): ProgramsDao
}