package com.pbarthuel.bodywellbeing.app.models

import com.google.firebase.database.IgnoreExtraProperties
import com.pbarthuel.bodywellbeing.data.vendors.local.room.exercises.entities.ExerciseEntity
import kotlinx.serialization.Serializable

@IgnoreExtraProperties
@Serializable
data class Exercise(
    val id: String,
    val image: String = "",
    val name: String,
    val description: String,
    val isFavorite: Boolean = false
) {
    fun toExerciseEntity(): ExerciseEntity =
        ExerciseEntity(
            id = id,
            image = image,
            name = name,
            description = description,
            isFavorite = isFavorite
        )
}