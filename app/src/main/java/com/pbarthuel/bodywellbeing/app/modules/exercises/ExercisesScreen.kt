package com.pbarthuel.bodywellbeing.app.modules.exercises

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pbarthuel.bodywellbeing.app.ui.theme.BodyWellBeingTheme
import com.pbarthuel.bodywellbeing.viewModel.modules.exercises.ExercisesViewModel

@Composable
fun ExercisesScreen(
    viewModel: ExercisesViewModel
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(BodyWellBeingTheme.colors.actionSecondary)
    ) {

    }
}