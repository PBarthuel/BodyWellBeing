package com.pbarthuel.bodywellbeing.data.vendors.local.room.programs.program.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class TaskEntity(
    @ColumnInfo(name = "localId")
    @PrimaryKey(autoGenerate = true)
    val localId: Int,
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "dayIndex")
    val dayIndex: Int,
    @ColumnInfo(name = "thumbnail")
    val thumbnail: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "type")
    val type: String
)