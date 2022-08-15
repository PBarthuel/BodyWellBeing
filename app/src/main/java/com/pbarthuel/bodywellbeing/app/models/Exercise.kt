package com.pbarthuel.bodywellbeing.app.models

import com.pbarthuel.bodywellbeing.data.model.WsExercise
import com.pbarthuel.bodywellbeing.data.vendors.local.room.exercises.customExercise.entities.CustomExerciseEntity
import com.pbarthuel.bodywellbeing.data.vendors.local.room.exercises.exercise.entities.ExerciseEntity
import kotlinx.serialization.Serializable

@Serializable
sealed class Exercise(
    open val id: String,
    open val image: String = "",
    open val name: String,
    open val description: String,
    open var isFavorite: Boolean = false,
    open val type: Int
) {
    data class Classic(
        override val id: String,
        override val image: String = "",
        override val name: String,
        override val description: String,
        override var isFavorite: Boolean = false,
        override val type: Int
    ) : Exercise(
        id = id,
        image = image,
        name = name,
        description = description,
        isFavorite = isFavorite,
        type = type
    )

    data class Custom(
        override val id: String,
        override val image: String = "",
        override val name: String,
        override val description: String,
        override var isFavorite: Boolean = false,
        override val type: Int
    ) : Exercise(
        id = id,
        image = image,
        name = name,
        description = description,
        isFavorite = isFavorite,
        type = type
    )

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

    fun toWs(isCustom: Boolean): WsExercise =
        WsExercise(
            id = id,
            image = image,
            name = name,
            description = description,
            type = type,
            isCustom = isCustom
        )
}