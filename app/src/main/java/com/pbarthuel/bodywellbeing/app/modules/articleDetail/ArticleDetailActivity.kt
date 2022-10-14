package com.pbarthuel.bodywellbeing.app.modules.articleDetail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.google.accompanist.insets.ProvideWindowInsets
import com.pbarthuel.bodywellbeing.app.ui.component.text.Body1
import com.pbarthuel.bodywellbeing.app.ui.component.text.Header1
import com.pbarthuel.bodywellbeing.app.ui.component.text.Header3
import com.pbarthuel.bodywellbeing.app.ui.theme.BodyWellBeingTheme
import com.pbarthuel.bodywellbeing.app.ui.theme.HorizontalMargin
import com.pbarthuel.bodywellbeing.app.ui.theme.VerticalMargin
import com.pbarthuel.bodywellbeing.viewModel.modules.articleDetail.ArticleDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleDetailActivity : ComponentActivity() {

    private val viewModel by viewModels<ArticleDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BodyWellBeingTheme {
                ProvideWindowInsets {
                    Scaffold(modifier = Modifier.fillMaxSize()) {
                        val articleDetail by viewModel.articleDetail.collectAsState(initial = null)
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            if (articleDetail != null) {
                                item {
                                    Image(
                                        modifier = Modifier.aspectRatio(1f),
                                        painter = rememberAsyncImagePainter(
                                            ImageRequest
                                                .Builder(context = LocalContext.current)
                                                .data(data = articleDetail!!.thumbnail)
                                                .build(),
                                        ),
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                    )
                                    Header1(
                                        text = articleDetail!!.title,
                                        modifier = Modifier.padding(top = VerticalMargin, start = HorizontalMargin, end = HorizontalMargin)
                                    )
                                }
                                items(articleDetail!!.sections) { section ->
                                    Column(modifier = Modifier.padding(horizontal = HorizontalMargin)) {
                                        Header3(
                                            text = section.title,
                                            modifier = Modifier.padding(vertical = VerticalMargin)
                                        )
                                        Body1(text = section.description)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}