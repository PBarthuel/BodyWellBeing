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
    val type: Int = 1,
    val isCustom: Boolean = false
) {
    fun toClassicDomain(): Exercise =
        Exercise.Classic(
            id = id,
            image = image,
            name = name,
            description = description,
            type = type
        )

    fun toCustomDomain(): Exercise.Custom =
        Exercise.Custom(
            id = id,
            image = image,
            name = name,
            description = description,
            type = type
        )
}