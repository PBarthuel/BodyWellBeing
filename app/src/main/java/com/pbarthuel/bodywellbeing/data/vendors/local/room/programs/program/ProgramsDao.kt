package com.pbarthuel.bodywellbeing.data.vendors.local.room.programs.program

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pbarthuel.bodywellbeing.data.vendors.local.room.programs.program.entities.ProgramEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProgramsDao {

    @Query("SELECT * FROM ProgramEntity")
    fun getAllPrograms(): Flow<List<ProgramEntity>?>

    @Query("SELECT * FROM ProgramEntity WHERE id == :programId")
    fun getProgramFromId(programId: String): Flow<ProgramEntity?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createProgram(programEntity: ProgramEntity)
}