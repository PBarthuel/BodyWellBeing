package com.pbarthuel.bodywellbeing.app.models

data class CondenseExercise(
    val id: String,
    val name: String,
    val isFavorite: Boolean = false,
    val type: Int
)