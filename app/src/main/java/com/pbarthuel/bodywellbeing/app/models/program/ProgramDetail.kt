package com.pbarthuel.bodywellbeing.app.models.program

import com.pbarthuel.bodywellbeing.app.models.task.Task
import kotlinx.serialization.Serializable

@Serializable
data class ProgramDetail(
    val programId: Long,
    val thumbnail: String,
    val title: String,
    val tasks: List<Task>,
    val maxWeekIndex: Int,
    val startDate: Long
)