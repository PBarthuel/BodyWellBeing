package com.pbarthuel.bodywellbeing.domain.model.program

import com.google.gson.annotations.SerializedName
import com.pbarthuel.bodywellbeing.data.model.program.WsProgramDetail
import kotlinx.serialization.Serializable

@Serializable
data class ProgramDetail(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("description") val description: String,
    @SerializedName("days") val days: List<Day>,
    @SerializedName("startDate") val startDate: Long? = null
) {

    fun toWs(): WsProgramDetail =
        WsProgramDetail(
            id = id,
            title = title,
            thumbnail = thumbnail,
            description = description,
            days = days.map { it.toWs() },
            startDate = startDate
        )
}