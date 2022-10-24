package com.pbarthuel.bodywellbeing.domain.model.program

import com.google.gson.annotations.SerializedName
import com.pbarthuel.bodywellbeing.data.model.program.WsDay
import kotlinx.serialization.Serializable

@Serializable
data class Day(
    @SerializedName("day") val day: Int,
    @SerializedName("tasks") val tasks: List<Task>
) {

    fun toWs(): WsDay =
        WsDay(
            dayIndex = day,
            tasks = tasks.map { it.toWs() }
        )
}