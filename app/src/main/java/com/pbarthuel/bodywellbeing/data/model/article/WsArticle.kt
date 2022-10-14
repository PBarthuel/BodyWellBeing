package com.pbarthuel.bodywellbeing.data.model.article

import com.google.gson.annotations.SerializedName
import com.pbarthuel.bodywellbeing.app.model.article.Article
import kotlinx.serialization.Serializable

@Serializable
data class WsArticle(
    @SerializedName("id") val id: String = "",
    @SerializedName("title") val title: String = "",
    @SerializedName("thumbnail") val thumbnail: String = "",
    @SerializedName("sections") val sections: List<WsArticleSection> = listOf()
) {

    fun toDomain(): Article =
        Article(
            id = id,
            title = title,
            thumbnail = thumbnail,
            sections = sections.map { it.toDomain() }
        )
}