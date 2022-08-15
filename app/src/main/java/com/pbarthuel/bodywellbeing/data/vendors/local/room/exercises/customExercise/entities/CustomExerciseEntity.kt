package com.pbarthuel.bodywellbeing.data.vendors.local.room.exercises.customExercise.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pbarthuel.bodywellbeing.app.models.Exercise
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class CustomExerciseEntity(
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
    val type: Int,
    @ColumnInfo(name = "is_sync")
    val isSync: Boolean = false
) {
    fun toExercise(): Exercise =
        Exercise.Custom(
            id = id,
            image = image,
            name = name,
            description = description,
            isFavorite = isFavorite,
            type = type
        )
}