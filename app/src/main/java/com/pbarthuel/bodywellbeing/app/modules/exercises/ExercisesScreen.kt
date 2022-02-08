package com.pbarthuel.bodywellbeing.app.modules.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.pbarthuel.bodywellbeing.R
import com.pbarthuel.bodywellbeing.app.ui.component.card.ExercisesCardSection
import com.pbarthuel.bodywellbeing.viewModel.modules.exercises.ExercisesViewModel

@Composable
fun ExercisesScreen(
    viewModel: ExercisesViewModel,
    onExerciseCardClicked: (String) -> Unit
) {
    val armExercises by viewModel.armExercises.collectAsState(initial = listOf())
    val tricepsExercises by viewModel.tricepsExercises.collectAsState(initial = listOf())
    val backExercises by viewModel.backExercises.collectAsState(initial = listOf())
    val shoulderExercises by viewModel.shoulderExercises.collectAsState(initial = listOf())
    val chestExercises by viewModel.chestExercises.collectAsState(initial = listOf())
    val absExercises by viewModel.absExercises.collectAsState(initial = listOf())
    val legExercises by viewModel.legExercises.collectAsState(initial = listOf())
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
    ) {
        ExercisesCardSection(
            title = stringResource(id = R.string.arm_exercises),
            exercises = armExercises,
            onCardClicked = { exerciseId -> onExerciseCardClicked(exerciseId) }
        )
        ExercisesCardSection(
            title = stringResource(id = R.string.triceps_exercises),
            exercises = tricepsExercises,
            onCardClicked = { exerciseId -> onExerciseCardClicked(exerciseId) }
        )
        ExercisesCardSection(
            title = stringResource(id = R.string.back_exercises),
            exercises = backExercises,
            onCardClicked = { exerciseId -> onExerciseCardClicked(exerciseId) }
        )
        ExercisesCardSection(
            title = stringResource(id = R.string.shoulder_exercises),
            exercises = shoulderExercises,
            onCardClicked = { exerciseId -> onExerciseCardClicked(exerciseId) }
        )
        ExercisesCardSection(
            title = stringResource(id = R.string.chest_exercises),
            exercises = chestExercises,
            onCardClicked = { exerciseId -> onExerciseCardClicked(exerciseId) }
        )
        ExercisesCardSection(
            title = stringResource(id = R.string.abs_exercises),
            exercises = absExercises,
            onCardClicked = { exerciseId -> onExerciseCardClicked(exerciseId) }
        )
        ExercisesCardSection(
            title = stringResource(id = R.string.leg_exercises),
            exercises = legExercises,
            onCardClicked = { exerciseId -> onExerciseCardClicked(exerciseId) }
        )
    }
}