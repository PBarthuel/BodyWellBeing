package com.pbarthuel.bodywellbeing.app.modules.exerciseDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pbarthuel.bodywellbeing.app.ui.component.text.Header4
import com.pbarthuel.bodywellbeing.app.ui.theme.BodyWellBeingTheme
import com.pbarthuel.bodywellbeing.viewModel.modules.exerciseDetail.ExerciseDetailViewModel

@Composable
fun ExerciseDetailScreen(
    viewModel: ExerciseDetailViewModel,
    exerciseId: String
) {
    viewModel.getExerciseForId(exerciseId)
    Column(modifier = Modifier
        .fillMaxSize()
        .background(BodyWellBeingTheme.colors.actionSecondary)) {
        Header4(text = viewModel.exercise?.name ?: "error")
    }
}