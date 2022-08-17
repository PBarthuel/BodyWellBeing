package com.pbarthuel.bodywellbeing.app.modules.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import com.pbarthuel.bodywellbeing.app.ui.component.StepGoalGauge

@Composable
fun HomeScreen() {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Column(modifier = Modifier.fillMaxWidth().wrapContentHeight()) {
                StepGoalGauge(
                    modifier = Modifier.align(CenterHorizontally),
                    progress = 0.7f,
                    textInside = "patate",
                    title = "saucisse",
                    animate = true
                )
            }
        }
    }
}