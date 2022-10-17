package com.pbarthuel.bodywellbeing.viewModel.modules.articleDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pbarthuel.bodywellbeing.app.model.article.Article
import com.pbarthuel.bodywellbeing.app.modules.main.MainActivity
import com.pbarthuel.bodywellbeing.domain.repositories.local.dataStore.PreferenceDataStoreRepository
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.articles.RoomArticlesRepository
import com.pbarthuel.bodywellbeing.domain.repositories.network.ArticleCloudFirestoreRepository
import com.pbarthuel.bodywellbeing.viewModel.utils.CoroutineToolsProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

@HiltViewModel
class ArticleDetailViewModel @Inject constructor(
    private val articleCloudFirestoreRepository: ArticleCloudFirestoreRepository,
    private val roomArticlesRepository: RoomArticlesRepository,
    private val preferenceDataStoreRepository: PreferenceDataStoreRepository,
    private val dispatcher: CoroutineToolsProvider,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var articleId: String
    private lateinit var userId: String

    init {
        articleId = savedStateHandle.get<String>(MainActivity.EXTRA_ARTICLE_ID)
            ?: throw Exception("ArticleId couldn't be null")
        viewModelScope.launch(dispatcher.io) {
            userId = preferenceDataStoreRepository.getUserId()
                ?: throw Exception("userId shouldn't be null")
        }
    }

    val articleDetail = roomArticlesRepository.getArticleFromId(articleId = articleId).flowOn(dispatcher.io)

    fun modifyFavoriteState(article: Article, isFavorite: Boolean) {
        viewModelScope.launch(dispatcher.io) {
            if (!isFavorite) {
                kotlin.runCatching {
                    articleCloudFirestoreRepository.addArticleToFavorite(
                        userId = userId,
                        article = article
                    )
                }.onSuccess {
                    roomArticlesRepository.updateIsFavorite(
                        articleId = article.id,
                        isFavorite = true
                    )
                }.onFailure {
                    //TODO ajouter un toast d'erreur pour dire a l'utilisateur qu'on ne peut pas ajouter de favoris hors ligne
                }
            } else {
                kotlin.runCatching {
                    articleCloudFirestoreRepository.deleteArticleFromFavorite(
                        userId = userId,
                        articleId = article.id
                    )
                }.onSuccess {
                    roomArticlesRepository.updateIsFavorite(
                        articleId = article.id,
                        isFavorite = false
                    )
                }.onFailure {
                    //TODO ajouter un toast d'erreur pour dire a l'utilisateur qu'on ne peut pas ajouter de favoris hors ligne
                }
            }
        }
    }
}