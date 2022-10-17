package com.pbarthuel.bodywellbeing.app.model.article

import com.pbarthuel.bodywellbeing.data.model.article.WsArticle
import com.pbarthuel.bodywellbeing.data.model.article.WsArticleSection
import kotlinx.serialization.Serializable

@Serializable
data class Article(
    val id: String,
    val title: String,
    val thumbnail: String,
    val sections: List<ArticleSection>,
    val isFavorite: Boolean = false
) {

    fun toWs() : WsArticle =
        WsArticle(
            id = id,
            title = title,
            thumbnail = thumbnail,
            sections = sections.map {
                WsArticleSection(
                    title = it.title,
                    description = it.description
                )
            }
        )
}