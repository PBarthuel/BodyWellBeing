package com.pbarthuel.bodywellbeing.app.modules.programOverview

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
import androidx.compose.material.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.google.accompanist.insets.ProvideWindowInsets
import com.pbarthuel.bodywellbeing.app.model.program.ProgramOverview
import com.pbarthuel.bodywellbeing.app.ui.component.button.ButtonFill
import com.pbarthuel.bodywellbeing.app.ui.component.text.Body1
import com.pbarthuel.bodywellbeing.app.ui.component.text.Header3
import com.pbarthuel.bodywellbeing.app.ui.theme.BodyWellBeingTheme
import com.pbarthuel.bodywellbeing.app.ui.theme.HorizontalMargin
import com.pbarthuel.bodywellbeing.app.ui.theme.VerticalMargin
import com.pbarthuel.bodywellbeing.viewModel.modules.programOverview.ProgramOverviewViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProgramOverviewActivity : ComponentActivity() {

    private val viewModel by viewModels<ProgramOverviewViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BodyWellBeingTheme {
                ProvideWindowInsets {
                    val programOverview = viewModel.programOverview.collectAsState(
                        initial = ProgramOverview(
                            programId = "",
                            thumbnail = "",
                            title = "",
                            description = ""
                        )
                    )
                    Scaffold(modifier = Modifier.fillMaxSize()) {
                        Column(modifier = Modifier.fillMaxSize()) {
                            LazyColumn(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxSize()
                            ) {
                                item {
                                    Column {
                                        Image(
                                            modifier = Modifier.aspectRatio(1f),
                                            painter = rememberAsyncImagePainter(
                                                ImageRequest
                                                    .Builder(context = LocalContext.current)
                                                    .data(data = programOverview.value?.thumbnail)
                                                    .build(),
                                            ),
                                            contentDescription = null,
                                            contentScale = ContentScale.Crop,
                                        )
                                        Header3(
                                            text = programOverview.value?.title ?: "",
                                            modifier = Modifier.padding(
                                                vertical = VerticalMargin,
                                                horizontal = HorizontalMargin
                                            )
                                        )
                                        Body1(
                                            text = programOverview.value?.description ?: "",
                                            modifier = Modifier.padding(horizontal = HorizontalMargin)
                                        )
                                    }
                                }
                            }
                            ButtonFill(
                                text = "Join",
                                modifier = Modifier.padding(
                                    vertical = VerticalMargin,
                                    horizontal = HorizontalMargin
                                )
                            ) { viewModel.joinProgram() }
                        }
                    }
                }
            }
        }
    }
}