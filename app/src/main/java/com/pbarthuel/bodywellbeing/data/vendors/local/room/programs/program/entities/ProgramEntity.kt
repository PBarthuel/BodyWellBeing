package com.pbarthuel.bodywellbeing.data.vendors.local.room.programs.program.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
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
    val description: String
)