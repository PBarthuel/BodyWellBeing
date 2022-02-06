package com.pbarthuel.bodywellbeing.data.vendors.local.room.exercises

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.pbarthuel.bodywellbeing.data.vendors.local.room.exercises.objectRequest.ExerciseRequest
import kotlinx.coroutines.flow.Flow

@Dao
interface ExercisesDao {

    @Query("SELECT * FROM ExerciseRequest")
    fun getExercises(): Flow<List<ExerciseRequest>?>

    @Insert
    suspend fun createExercise(exerciseRequest: ExerciseRequest)

    @Update
    suspend fun updateExercise(exerciseRequest: ExerciseRequest)

    @Query("DELETE FROM ExerciseRequest")
    suspend fun clearExercisesDb()
}