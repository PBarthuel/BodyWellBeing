package com.pbarthuel.bodywellbeing.data.model.program

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class WsProgram(
    @SerializedName("id") val id: String = "",
    @SerializedName("title") val title: String = "",
    @SerializedName("thumbnail") val thumbnail: String = "",
    @SerializedName("description") val description: String = "",
    @SerializedName("days") val days: List<WsDay> = listOf()
)