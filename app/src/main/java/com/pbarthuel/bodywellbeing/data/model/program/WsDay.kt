package com.pbarthuel.bodywellbeing.data.model.program

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class WsDay(
    @SerializedName("dayIndex") val dayIndex: Int = 0,
    @SerializedName("tasks") val tasks: List<WsTask> = listOf()
)