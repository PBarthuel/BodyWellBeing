package com.pbarthuel.bodywellbeing.data.vendors.local.room.programs.program.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pbarthuel.bodywellbeing.app.model.program.ProgramOverview
import com.pbarthuel.bodywellbeing.app.model.program.ProgramPreview
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class ProgramEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "title")
    val title: String = "",
    @ColumnInfo(name = "thumbnail")
    val thumbnail: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "startDate")
    val startDate: Long = 0,
) {
    fun toProgramPreview(): ProgramPreview =
        ProgramPreview(
            programId = id,
            thumbnail = thumbnail,
            title = title
        )

    fun toProgramOverview(): ProgramOverview =
        ProgramOverview(
            programId = id,
            thumbnail = thumbnail,
            title = title,
            description = description
        )
}