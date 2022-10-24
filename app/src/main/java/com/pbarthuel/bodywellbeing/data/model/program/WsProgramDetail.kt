package com.pbarthuel.bodywellbeing.data.model.program

import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName
import com.pbarthuel.bodywellbeing.domain.model.program.ProgramDetail
import kotlinx.serialization.Serializable

@Serializable
@IgnoreExtraProperties
data class WsProgramDetail(
    @SerializedName("id") val id: String = "",
    @SerializedName("title") val title: String = "",
    @SerializedName("thumbnail") val thumbnail: String = "",
    @SerializedName("description") val description: String = "",
    @SerializedName("days") val days: List<WsDay> = listOf(),
    @SerializedName("startDate") val startDate: Long? = null
) {

    fun toDomain(): ProgramDetail =
        ProgramDetail(
            id = id,
            title = title,
            thumbnail = thumbnail,
            description = description,
            days = days.map { it.toDomain() },
            startDate = startDate
        )
}