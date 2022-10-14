package com.pbarthuel.bodywellbeing.data.model.article

import com.google.gson.annotations.SerializedName
import com.pbarthuel.bodywellbeing.app.model.article.ArticleSection
import kotlinx.serialization.Serializable

@Serializable
data class WsArticleSection(
    @SerializedName("title") val title: String = "",
    @SerializedName("description") val description: String = ""
) {

    fun toDomain(): ArticleSection =
        ArticleSection(
            title = title,
            description = description
        )
}
