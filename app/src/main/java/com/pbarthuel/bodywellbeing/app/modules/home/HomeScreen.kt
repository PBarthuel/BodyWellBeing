package com.pbarthuel.bodywellbeing.app.modules.home

import android.content.Intent
import androidx.compose.animation.ExperimentalAnimationApi
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.pbarthuel.bodywellbeing.app.model.program.ProgramOverview
import com.pbarthuel.bodywellbeing.app.model.program.ProgramPreview
import com.pbarthuel.bodywellbeing.app.modules.main.MainActivity
import com.pbarthuel.bodywellbeing.app.modules.programOverview.ProgramOverviewActivity
import com.pbarthuel.bodywellbeing.app.ui.component.StepGoalGauge
import com.pbarthuel.bodywellbeing.app.ui.component.card.ProgramCard
import com.pbarthuel.bodywellbeing.app.ui.component.text.Header3
import com.pbarthuel.bodywellbeing.app.ui.theme.HorizontalMargin
import com.pbarthuel.bodywellbeing.app.ui.theme.VerticalMargin
import com.pbarthuel.bodywellbeing.viewModel.modules.home.HomeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class, ExperimentalAnimationApi::class, ExperimentalCoroutinesApi::class)
@Composable
fun HomeScreenWithoutEnrolledProgram(
    viewModel: HomeViewModel = hiltViewModel(),
    activityTrackPermissionState: State<Boolean>,
    onStepGaugeClick: () -> Unit
) {
    val programsPreviews = viewModel.programsPreviews.collectAsState(initial = listOf())
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = HorizontalMargin),
        verticalArrangement = Arrangement.spacedBy(VerticalMargin)
    ) {
        item {
            Column(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()) {
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
                context.startActivity(
                    Intent(context, ProgramOverviewActivity::class.java)
                        .putExtra(MainActivity.EXTRA_PROGRAM_ID, item.programId)
                )
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
fun HomeScreenWithEnrolledProgram(
    activityTrackPermissionState: State<Boolean>,
    onStepGaugeClick: () -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Column(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()) {
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
    }
}