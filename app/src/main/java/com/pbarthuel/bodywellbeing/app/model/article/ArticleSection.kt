package com.pbarthuel.bodywellbeing.app.model.article
import kotlinx.serialization.Serializable

@Serializable
data class ArticleSection(
    val title: String,
    val description: String
)
