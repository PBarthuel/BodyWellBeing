package com.pbarthuel.bodywellbeing.data.model.program

import com.google.gson.annotations.SerializedName
import com.pbarthuel.bodywellbeing.domain.model.program.Task
import kotlinx.serialization.Serializable

@Serializable
data class WsTask(
    @SerializedName("id") val id: String = "",
    @SerializedName("title") val title: String = "",
    @SerializedName("thumbnail") val thumbnail: String = "",
    @SerializedName("type") val type: Int = 0
) {

    fun toDomain(): Task =
        Task(
            id = id,
            thumbnail = thumbnail,
            type = type,
            title = title
        )
}