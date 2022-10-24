package com.pbarthuel.bodywellbeing.domain.model.program

import com.google.gson.annotations.SerializedName
import com.pbarthuel.bodywellbeing.data.model.program.WsTask
import kotlinx.serialization.Serializable

@Serializable
data class Task(
    @SerializedName("id") val id: String,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("type") val type: Int,
    @SerializedName("title") val title: String,
    @SerializedName("dayIndex") val dayIndex: Int? = null,
) {

    fun toWs(): WsTask =
        WsTask(
            id = id,
            thumbnail = thumbnail,
            type = type,
            title = title
        )
}