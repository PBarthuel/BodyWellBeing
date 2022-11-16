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

    @Query("UPDATE ProgramEntity SET startDate = :startDate WHERE id == :programId")
    fun joinProgram(programId: String, startDate: Long)

    @Query("SELECT * FROM ProgramEntity WHERE startDate != 0")
    fun isProgramJoined(): ProgramEntity? //TODO peut être changer ça

    @Query("SELECT * FROM ProgramEntity WHERE startDate != 0")
    fun getJoinedProgram(): Flow<ProgramEntity?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createProgram(programEntity: ProgramEntity)

    @Query("UPDATE ProgramEntity SET startDate = 0 WHERE id == :programId")
    fun clearProgramsStartDate(programId: String)

    @Query("DELETE FROM ProgramEntity")
    suspend fun clearProgramsDb()
}