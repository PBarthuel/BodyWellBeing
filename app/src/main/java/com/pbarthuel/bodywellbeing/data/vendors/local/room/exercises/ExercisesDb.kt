package com.pbarthuel.bodywellbeing.data.vendors.local.room.exercises

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pbarthuel.bodywellbeing.data.vendors.local.room.exercises.customExercise.CustomExerciseDao
import com.pbarthuel.bodywellbeing.data.vendors.local.room.exercises.customExercise.entities.CustomExerciseEntity
import com.pbarthuel.bodywellbeing.data.vendors.local.room.exercises.exercise.ExercisesDao
import com.pbarthuel.bodywellbeing.data.vendors.local.room.exercises.exercise.entities.ExerciseEntity

@Database(
    version = 2,
    entities = [
        (ExerciseEntity::class),
        (CustomExerciseEntity::class)
    ]
)
abstract class ExercisesDb : RoomDatabase() {
    abstract fun ExercisesDao(): ExercisesDao
    abstract fun CustomExerciseDao(): CustomExerciseDao
}