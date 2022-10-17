package com.pbarthuel.bodywellbeing.data.vendors.local.room.article.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pbarthuel.bodywellbeing.app.model.article.Article
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@Entity
@Serializable
data class ArticleEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "thumbnail")
    val thumbnail: String,
    @ColumnInfo(name = "sections")
    val sections: String,
    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean = false
) {

    @OptIn(ExperimentalSerializationApi::class)
    fun toDomain(): Article =
        Article(
            id = id,
            title = title,
            thumbnail = thumbnail,
            sections = Json.decodeFromString(sections),
            isFavorite = isFavorite
        )
}