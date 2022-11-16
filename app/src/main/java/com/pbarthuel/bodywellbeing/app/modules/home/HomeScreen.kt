package com.pbarthuel.bodywellbeing.app.modules.home

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.pbarthuel.bodywellbeing.app.model.article.Article
import com.pbarthuel.bodywellbeing.app.model.program.ProgramPreview
import com.pbarthuel.bodywellbeing.app.ui.component.StepGoalGauge
import com.pbarthuel.bodywellbeing.app.ui.component.card.ArticleCard
import com.pbarthuel.bodywellbeing.app.ui.component.card.ProgramCard
import com.pbarthuel.bodywellbeing.app.ui.component.text.Header3
import com.pbarthuel.bodywellbeing.app.ui.theme.HorizontalMargin
import com.pbarthuel.bodywellbeing.app.ui.theme.VerticalMargin
import com.pbarthuel.bodywellbeing.domain.model.program.Task
import com.pbarthuel.bodywellbeing.viewModel.modules.home.HomeViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    activityTrackPermissionState: State<Boolean>,
    onProgramCardClicked: (ProgramPreview) -> Unit,
    onStepGaugeClick: () -> Unit
) {
    val tasks = viewModel.programTasks.collectAsState(initial = listOf())
    Crossfade(targetState = tasks.value) { tasksList ->
        when (tasksList.isEmpty()) {
            true -> HomeScreenWithoutEnrolledProgram(
                viewModel = viewModel,
                activityTrackPermissionState = activityTrackPermissionState,
                onProgramCardClicked = onProgramCardClicked
            ) {

            }
            else -> HomeScreenWithEnrolledProgram(
                activityTrackPermissionState = activityTrackPermissionState,
                tasks = tasksList
            ) {

            }
        }
    }
}

val programs = listOf(
    ProgramPreview(
        programId = "1",
        thumbnail = "https://www.spirulinefrance.fr/wp-content/uploads/2020/09/importance-du-sport-sante.png",
        title = "Saucisse"
    ),
    ProgramPreview(
        programId = "1",
        thumbnail = "https://www.spirulinefrance.fr/wp-content/uploads/2020/09/importance-du-sport-sante.png",
        title = "Saucisse"
    ),
    ProgramPreview(
        programId = "1",
        thumbnail = "https://www.spirulinefrance.fr/wp-content/uploads/2020/09/importance-du-sport-sante.png",
        title = "Saucisse"
    ),
    ProgramPreview(
        programId = "1",
        thumbnail = "https://www.spirulinefrance.fr/wp-content/uploads/2020/09/importance-du-sport-sante.png",
        title = "Saucisse"
    ),
    ProgramPreview(
        programId = "1",
        thumbnail = "https://www.spirulinefrance.fr/wp-content/uploads/2020/09/importance-du-sport-sante.png",
        title = "Saucisse"
    )
)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreenWithoutEnrolledProgram(
    viewModel: HomeViewModel,
    activityTrackPermissionState: State<Boolean>,
    onProgramCardClicked: (ProgramPreview) -> Unit,
    onStepGaugeClick: () -> Unit
) {
    val programsPreviews = viewModel.programsPreviews.collectAsState(initial = listOf())
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = HorizontalMargin),
        verticalArrangement = Arrangement.spacedBy(VerticalMargin)
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                StepGoalGauge(
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = null
                        ) {
                            if (!activityTrackPermissionState.value) {
                                onStepGaugeClick()
                            }
                        },
                    progress = 0.7f,
                    textInside = if (activityTrackPermissionState.value) "Cool" else "",
                    title = if (activityTrackPermissionState.value) "Cool" else "Click here to grant permission",
                    animate = true
                )
            }
        }
        item { Header3(text = "Programs") }
        items(programsPreviews.value ?: listOf()) { item ->
            ProgramCard(programPreview = item) {
                onProgramCardClicked(item)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreenWithEnrolledProgram(
    activityTrackPermissionState: State<Boolean>,
    tasks: List<Task>,
    onStepGaugeClick: () -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                StepGoalGauge(
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = null
                        ) {
                            if (!activityTrackPermissionState.value) {
                                onStepGaugeClick()
                            }
                        },
                    progress = 0.7f,
                    textInside = if (activityTrackPermissionState.value) "Cool" else "",
                    title = if (activityTrackPermissionState.value) "Cool" else "Click here to grant permission",
                    animate = true
                )
            }
        }
        items(tasks) { task ->
            ArticleCard(
                article = Article(
                    id = task.id,
                    title = task.title,
                    thumbnail = task.thumbnail,
                    listOf()
                )
            ) {

            }
        }
    }
}