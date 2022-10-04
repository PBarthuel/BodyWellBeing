package com.pbarthuel.bodywellbeing.app.model.task

import com.pbarthuel.bodywellbeing.app.model.Exercise
import kotlinx.serialization.Serializable

@Serializable
data class Task(
    val taskId: Long,
    val exercise: Exercise
)