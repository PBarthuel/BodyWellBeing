package com.pbarthuel.bodywellbeing.app.modules.exerciseDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pbarthuel.bodywellbeing.R
import com.pbarthuel.bodywellbeing.app.ui.component.text.Body1
import com.pbarthuel.bodywellbeing.app.ui.component.text.Header1
import com.pbarthuel.bodywellbeing.app.ui.component.text.Header2
import com.pbarthuel.bodywellbeing.app.ui.theme.Basic1
import com.pbarthuel.bodywellbeing.app.ui.theme.BodyWellBeingTheme
import com.pbarthuel.bodywellbeing.app.ui.theme.HorizontalMargin
import com.pbarthuel.bodywellbeing.app.ui.theme.Layout1
import com.pbarthuel.bodywellbeing.app.ui.theme.VerticalMargin
import com.pbarthuel.bodywellbeing.viewModel.modules.exerciseDetail.ExerciseDetailViewModel

@Composable
fun ExerciseDetailScreen(
    viewModel: ExerciseDetailViewModel,
    exerciseId: String,
    onNavigationBackClicked: () -> Unit,
) {
    viewModel.getExerciseForId(exerciseId)
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
        .background(BodyWellBeingTheme.colors.actionSecondary)
    ) {
        TopAppBar(
            modifier = Modifier.padding(Basic1),
            backgroundColor = Color.Transparent.copy(alpha = 0f),
            elevation = 2.dp,
            title = { Header2(text = stringResource(id = R.string.exercise_detail)) },
            navigationIcon = {
                IconButton(
                    onClick = onNavigationBackClicked
                ) { Icon(Icons.Filled.ArrowBack, contentDescription = "Settings") }
            },
            actions = { }
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = VerticalMargin, horizontal = HorizontalMargin)
        ) {
            Header1(modifier = Modifier
                .align(CenterHorizontally)
                .padding(bottom = Layout1), text = viewModel.exercise?.name ?: "error")
            Body1(text = viewModel.exercise?.description ?: "error")
        }
    }
}