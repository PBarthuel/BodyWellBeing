package com.pbarthuel.bodywellbeing.data.model

import com.google.firebase.firestore.IgnoreExtraProperties
import kotlinx.serialization.Serializable
import com.pbarthuel.bodywellbeing.app.models.Exercise

@Serializable
@IgnoreExtraProperties
@com.google.firebase.database.IgnoreExtraProperties
data class WsExercise(
    val id: String = "",
    val image: String = "",
    val name: String = "",
    val description: String = "",
    val type: Int = 1
) {
    fun toDomain(): Exercise =
        Exercise(
            id = id,
            image = image,
            name = name,
            description = description,
            type = type
        )
}