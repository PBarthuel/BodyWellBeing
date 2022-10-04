package com.pbarthuel.bodywellbeing.domain.model.program

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Task(
    @SerializedName("id") val id: Int,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("type") val type: String,
    @SerializedName("title") val title: String
)