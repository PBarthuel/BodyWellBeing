package com.pbarthuel.bodywellbeing.app.models.program

import kotlinx.serialization.Serializable

@Serializable
data class ProgramPreview(
    val programId: Long,
    val thumbnail: String,
    val title: String,
    val state: ProgramState
)