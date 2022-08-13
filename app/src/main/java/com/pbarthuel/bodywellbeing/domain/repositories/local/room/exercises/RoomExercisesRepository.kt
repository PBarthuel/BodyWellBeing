package com.pbarthuel.bodywellbeing.domain.repositories.local.room.exercises

import com.pbarthuel.bodywellbeing.app.models.CondenseExercise
import com.pbarthuel.bodywellbeing.app.models.Exercise
import kotlinx.coroutines.flow.Flow

interface RoomExercisesRepository {
    fun getAllExercises(): Flow<List<Exercise>>
    fun getExerciseFromId(exerciseId: String): Flow<Exercise>
    fun getAllCondenseExercises(): Flow<List<CondenseExercise>>
    fun getArmExercises(): Flow<List<CondenseExercise>>
    fun getTricepsExercises(): Flow<List<CondenseExercise>>
    fun getBackExercises(): Flow<List<CondenseExercise>>
    fun getShoulderExercises(): Flow<List<CondenseExercise>>
    fun getChestExercises(): Flow<List<CondenseExercise>>
    fun getAbsExercises(): Flow<List<CondenseExercise>>
    fun getLegExercises(): Flow<List<CondenseExercise>>
    fun getFavoritesExercises(): Flow<List<CondenseExercise>>
    suspend fun createExercise(exercise: Exercise)
    suspend fun updateExercise(exercise: Exercise)
    suspend fun updateIsFavorite(exerciseId: String, isFavorite: Boolean)
    suspend fun clearExercisesDb()
}