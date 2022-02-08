package com.pbarthuel.bodywellbeing.data.vendors.local.room.exercises

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.pbarthuel.bodywellbeing.data.vendors.local.room.exercises.entities.ExerciseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExercisesDao {

    @Query("SELECT * FROM ExerciseEntity")
    fun getExercises(): Flow<List<ExerciseEntity>?>

    @Query("SELECT * FROM ExerciseEntity WHERE is_favorite == 1")
    fun getFavoritesExercises(): Flow<List<ExerciseEntity>?>

    @Insert
    suspend fun createExercise(exerciseEntity: ExerciseEntity)

    @Update
    suspend fun updateExercise(exerciseEntity: ExerciseEntity)

    @Query("DELETE FROM ExerciseEntity")
    suspend fun clearExercisesDb()
}