package com.pbarthuel.bodywellbeing.data.vendors.local.room.exercises

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.pbarthuel.bodywellbeing.data.vendors.local.room.exercises.entities.CondenseExerciseEntity
import com.pbarthuel.bodywellbeing.data.vendors.local.room.exercises.entities.ExerciseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExercisesDao {

    @Query("SELECT * FROM ExerciseEntity")
    fun getAllExercises(): Flow<List<ExerciseEntity>?>

    @Query("SELECT * FROM ExerciseEntity WHERE id == :exerciseId")
    suspend fun getExerciseFromId(exerciseId: String): ExerciseEntity?

    @Query("SELECT * FROM ExerciseEntity WHERE type == :exerciseType")
    fun getCondenseExercisesFromType(exerciseType: Int): Flow<List<CondenseExerciseEntity>?>

    @Query("SELECT * FROM ExerciseEntity WHERE is_favorite == 1")
    fun getCondenseFavoritesExercises(): Flow<List<CondenseExerciseEntity>?>

    @Insert
    suspend fun createExercise(exerciseEntity: ExerciseEntity)

    @Update
    suspend fun updateExercise(exerciseEntity: ExerciseEntity)

    @Query("DELETE FROM ExerciseEntity")
    suspend fun clearExercisesDb()
}