package com.pbarthuel.bodywellbeing.app.model.program

import kotlinx.serialization.Serializable

@Serializable
data class ProgramPreview(
    val programId: String,
    val thumbnail: String,
    val title: String
)