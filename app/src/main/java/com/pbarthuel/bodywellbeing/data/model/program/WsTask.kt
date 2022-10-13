package com.pbarthuel.bodywellbeing.data.model.program

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class WsTask(
    @SerializedName("id") val id: String = "",
    @SerializedName("type") val type: String = ""
)