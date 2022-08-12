package com.pbarthuel.bodywellbeing.app.modules.body

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.pbarthuel.bodywellbeing.app.ui.component.card.ExerciseCard
import com.pbarthuel.bodywellbeing.app.ui.component.card.ExercisesCardSection
import com.pbarthuel.bodywellbeing.app.ui.component.text.Header3
import com.pbarthuel.bodywellbeing.app.ui.theme.HorizontalMargin
import com.pbarthuel.bodywellbeing.viewModel.modules.exercises.ExercisesViewModel

@Composable
fun BodyScreen(
    viewModel: ExercisesViewModel = hiltViewModel()
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {

    }
}