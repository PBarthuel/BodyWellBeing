package com.pbarthuel.bodywellbeing.domain.repositories.network

import com.pbarthuel.bodywellbeing.app.model.article.Article
import kotlinx.coroutines.flow.Flow

interface ArticleCloudFirestoreRepository {
    fun getAllArticles(): Flow<List<Article>>
    fun createArticle(article: Article)
}