package com.pbarthuel.bodywellbeing.app.modules.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import com.pbarthuel.bodywellbeing.app.ui.component.StepGoalGauge

@Composable
fun HomeScreen(
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