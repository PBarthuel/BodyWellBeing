package com.pbarthuel.bodywellbeing.data.vendors.local.room.exercises.customExercise

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.pbarthuel.bodywellbeing.data.vendors.local.room.exercises.customExercise.entities.CustomExerciseEntity
import com.pbarthuel.bodywellbeing.data.vendors.local.room.exercises.exercise.entities.CondenseExerciseEntity
import com.pbarthuel.bodywellbeing.data.vendors.local.room.exercises.exercise.entities.ExerciseEntity
import kotlinx.coroutines.flow.Flow

interface CustomExerciseDao {

    @Query("SELECT * FROM CustomExerciseEntity")
    fun getAllExercises(): Flow<List<CustomExerciseEntity>?>

    @Query("SELECT * FROM CustomExerciseEntity WHERE id == :exerciseId")
    fun getExerciseFromId(exerciseId: String): Flow<CustomExerciseEntity?>

    @Query("SELECT * FROM CustomExerciseEntity")
    fun getAllCondenseExercises(): Flow<List<CondenseExerciseEntity>?>

    @Query("SELECT * FROM CustomExerciseEntity WHERE type == :exerciseType")
    fun getCondenseExercisesFromType(exerciseType: Int): Flow<List<CondenseExerciseEntity>?>

    @Query("SELECT * FROM CustomExerciseEntity WHERE is_favorite == 1")
    fun getCondenseFavoritesExercises(): Flow<List<CondenseExerciseEntity>?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createExercise(customExerciseEntity: CustomExerciseEntity)

    @Update
    suspend fun updateExercise(exerciseEntity: CustomExerciseEntity)

    @Query("UPDATE CustomExerciseEntity SET is_favorite = :isFavorite WHERE id == :exerciseId")
    suspend fun updateIsFavorite(exerciseId: String, isFavorite: Boolean)

    @Query("DELETE FROM CustomExerciseEntity")
    suspend fun clearExercisesDb()
}