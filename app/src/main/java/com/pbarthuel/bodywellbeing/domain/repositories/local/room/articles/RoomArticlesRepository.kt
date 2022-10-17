package com.pbarthuel.bodywellbeing.domain.repositories.local.room.articles

import com.pbarthuel.bodywellbeing.app.model.article.Article
import kotlinx.coroutines.flow.Flow

interface RoomArticlesRepository {
    fun getAllArticles(): Flow<List<Article>>
    fun getArticleDetail(articleId: String): Flow<Article?>
    fun getFavoritesExercises(): Flow<List<Article>>
    suspend fun createArticle(article: Article)
    suspend fun updateIsFavorite(articleId: String, isFavorite: Boolean)
}