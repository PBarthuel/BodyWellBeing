package com.pbarthuel.bodywellbeing.viewModel.modules.articleDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.pbarthuel.bodywellbeing.app.modules.main.MainActivity
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.articles.RoomArticlesRepository
import com.pbarthuel.bodywellbeing.viewModel.utils.CoroutineToolsProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.flowOn

@HiltViewModel
class ArticleDetailViewModel @Inject constructor(
    private val roomArticlesRepository: RoomArticlesRepository,
    private val dispatcher: CoroutineToolsProvider,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var articleId: String

    init {
        articleId = savedStateHandle.get<String>(MainActivity.EXTRA_ARTICLE_ID)
            ?: throw Exception("ArticleId couldn't be null")
    }

    val articleDetail = roomArticlesRepository.getArticleDetail(articleId = articleId).flowOn(dispatcher.io)
}