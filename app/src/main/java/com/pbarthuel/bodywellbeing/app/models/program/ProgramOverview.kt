package com.pbarthuel.bodywellbeing.app.models.program

import kotlinx.serialization.Serializable

@Serializable
data class ProgramOverview(
    val programId: Long,
    val thumbnail: String,
    val title: String,
    val description: String,
    val maxWeekIndex: Int
)