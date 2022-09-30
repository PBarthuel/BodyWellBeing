package com.pbarthuel.bodywellbeing.app.models.task

import com.pbarthuel.bodywellbeing.app.models.Exercise
import kotlinx.serialization.Serializable

@Serializable
data class Task(
    val taskId: Long,
    val exercise: Exercise
)