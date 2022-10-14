package com.pbarthuel.bodywellbeing.data.repositories.local.room.articles

import com.pbarthuel.bodywellbeing.app.model.article.Article
import com.pbarthuel.bodywellbeing.data.vendors.local.room.article.ArticleDao
import com.pbarthuel.bodywellbeing.data.vendors.local.room.article.entities.ArticleEntity
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.articles.RoomArticlesRepository
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class RoomArticlesRepositoryImpl @Inject constructor(
    private val articleDao: ArticleDao
) : RoomArticlesRepository {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getAllArticles(): Flow<List<Article>> =
        articleDao.getAllExercises().mapLatest { entities ->
            entities.map { it.toDomain() }
        }

    override fun getArticleDetail(articleId: String): Flow<Article?> =
        articleDao.getArticleFromId(articleId = articleId).mapLatest {
            it?.toDomain()
        }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun createArticle(article: Article) =
        articleDao.createExercise(
            ArticleEntity(
                id = article.id,
                title = article.title,
                thumbnail = article.thumbnail,
                sections = Json.encodeToString(article.sections)
            )
        )
}