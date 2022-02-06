package com.pbarthuel.bodywellbeing.data.vendors.local.room.exercises

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pbarthuel.bodywellbeing.data.vendors.local.room.exercises.entities.ExerciseEntity

@Database(entities = [(ExerciseEntity::class)], version = 1)
abstract class ExercisesDb: RoomDatabase() {
    abstract fun ExercisesDao(): ExercisesDao
}