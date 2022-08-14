package com.pbarthuel.bodywellbeing.data.vendors.local.room.exercises.exercise

import androidx.room.Dao
import androidx.room.Ignore
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.pbarthuel.bodywellbeing.data.vendors.local.room.exercises.exercise.entities.CondenseExerciseEntity
import com.pbarthuel.bodywellbeing.data.vendors.local.room.exercises.exercise.entities.ExerciseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExercisesDao {

    @Query("SELECT * FROM ExerciseEntity")
    fun getAllExercises(): Flow<List<ExerciseEntity>?>

    @Query("SELECT * FROM ExerciseEntity WHERE id == :exerciseId")
    fun getExerciseFromId(exerciseId: String): Flow<ExerciseEntity>?

    @Query("SELECT * FROM ExerciseEntity")
    fun getAllCondenseExercises(): Flow<List<CondenseExerciseEntity>?>

    @Query("SELECT * FROM ExerciseEntity WHERE type == :exerciseType")
    fun getCondenseExercisesFromType(exerciseType: Int): Flow<List<CondenseExerciseEntity>?>

    @Query("SELECT * FROM ExerciseEntity WHERE is_favorite == 1")
    fun getCondenseFavoritesExercises(): Flow<List<CondenseExerciseEntity>?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createExercise(exerciseEntity: ExerciseEntity)

    @Query("UPDATE ExerciseEntity SET is_favorite = :isFavorite WHERE id == :exerciseId")
    suspend fun updateIsFavorite(exerciseId: String, isFavorite: Boolean)

    @Query("UPDATE ExerciseEntity SET is_favorite = 0 WHERE is_favorite == 1")
    suspend fun resetAllIsFavoriteAtLogout()

    @Query("DELETE FROM ExerciseEntity")
    suspend fun clearExercisesDb()
}