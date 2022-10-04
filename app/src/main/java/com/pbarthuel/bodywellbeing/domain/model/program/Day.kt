package com.pbarthuel.bodywellbeing.domain.model.program

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Day(
    @SerializedName("day") val day: Int,
    @SerializedName("tasks") val tasks: List<Task>
)