package com.pbarthuel.bodywellbeing.data.vendors.local.room.exercises.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.pbarthuel.bodywellbeing.app.models.CondenseExercise
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class CondenseExerciseEntity(
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean = false,
    @ColumnInfo(name = "type")
    val type: Int
) {
    fun toCondenseExercise(): CondenseExercise =
        CondenseExercise(
            id = id,
            name = name,
            isFavorite = isFavorite,
            type = type
        )
}