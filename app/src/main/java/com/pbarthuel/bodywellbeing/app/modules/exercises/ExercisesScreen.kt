package com.pbarthuel.bodywellbeing.app.modules.exercises

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.pbarthuel.bodywellbeing.app.ui.component.card.ExercisesCardSection
import com.pbarthuel.bodywellbeing.viewModel.modules.exercises.ExercisesViewModel

@Composable
fun ExercisesScreen(
    viewModel: ExercisesViewModel,
    onExerciseCardClicked: (String) -> Unit
) {
    val exercisesGroupByType by viewModel.exercisesGroupByType.collectAsState(mapOf())
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        exercisesGroupByType.forEach { (_, exercises) ->
            item {
                ExercisesCardSection(
                    exercises = exercises,
                    onCardClicked = { exerciseId -> onExerciseCardClicked(exerciseId) }
                )
            }
        }
    }
}