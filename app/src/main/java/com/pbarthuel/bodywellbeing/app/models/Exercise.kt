package com.pbarthuel.bodywellbeing.app.models

import com.pbarthuel.bodywellbeing.data.model.WsExercise
import com.pbarthuel.bodywellbeing.data.vendors.local.room.exercises.customExercise.entities.CustomExerciseEntity
import com.pbarthuel.bodywellbeing.data.vendors.local.room.exercises.exercise.entities.ExerciseEntity
import kotlinx.serialization.Serializable

@Serializable
data class Exercise(
    val id: String,
    val image: String = "",
    val name: String,
    val description: String,
    var isFavorite: Boolean = false,
    val type: Int
) {
    fun toExerciseEntity(): ExerciseEntity =
        ExerciseEntity(
            id = id,
            image = image,
            name = name,
            description = description,
            isFavorite = isFavorite,
            type = type
        )

    fun toCustomExerciseEntity(isSync: Boolean): CustomExerciseEntity =
        CustomExerciseEntity(
            id = id,
            image = image,
            name = name,
            description = description,
            isFavorite = isFavorite,
            type = type,
            isSync = isSync
        )

    fun toWs(): WsExercise =
        WsExercise(
            id = id,
            image = image,
            name = name,
            description = description,
            type = type
        )
}