package com.pbarthuel.bodywellbeing.data.repositories.network

import com.pbarthuel.bodywellbeing.app.model.article.Article
import com.pbarthuel.bodywellbeing.data.model.article.WsArticle
import com.pbarthuel.bodywellbeing.data.model.article.WsArticleSection
import com.pbarthuel.bodywellbeing.data.vendors.network.ArticleCloudFirestoreDao
import com.pbarthuel.bodywellbeing.domain.repositories.network.ArticleCloudFirestoreRepository
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest

class ArticleCloudFirestoreRepositoryImpl @Inject constructor(
    private val articleCloudFirestoreDao: ArticleCloudFirestoreDao
) : ArticleCloudFirestoreRepository {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getAllArticles(): Flow<List<Article>> =
        articleCloudFirestoreDao.getAllArticles().mapLatest { wsArticle ->
            wsArticle.map { wsExercise -> wsExercise.toDomain() }
        }

    override fun createArticle(article: Article) =
        articleCloudFirestoreDao.createArticle(
            article = WsArticle(
                id = article.id,
                title = article.title,
                thumbnail = article.thumbnail,
                sections = article.sections.map {
                    WsArticleSection(
                        title = it.title,
                        description = it.description
                    )
                }
            )
        )
}