package com.pbarthuel.bodywellbeing.app.model.article

import kotlinx.serialization.Serializable

@Serializable
data class Article(
    val id: String,
    val title: String,
    val thumbnail: String,
    val sections: List<ArticleSection>
)