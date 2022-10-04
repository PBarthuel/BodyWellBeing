package com.pbarthuel.bodywellbeing.data.vendors.local.room.exercises.exercise.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pbarthuel.bodywellbeing.app.model.Exercise
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class ExerciseEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "image")
    val image: String = "",
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean = false,
    @ColumnInfo(name = "type")
    val type: Int
) {
    fun toExercise(): Exercise =
        Exercise.Classic(
            id = id,
            image = image,
            name = name,
            description = description,
            isFavorite = isFavorite,
            type = type
        )
}