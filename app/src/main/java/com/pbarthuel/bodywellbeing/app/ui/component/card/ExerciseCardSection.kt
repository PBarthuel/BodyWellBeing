package com.pbarthuel.bodywellbeing.app.ui.component.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.pbarthuel.bodywellbeing.R
import com.pbarthuel.bodywellbeing.app.models.Exercise
import com.pbarthuel.bodywellbeing.app.ui.component.text.Header2
import com.pbarthuel.bodywellbeing.app.ui.theme.Basic2
import com.pbarthuel.bodywellbeing.app.ui.theme.HorizontalMargin

@Composable
fun ExercisesCardSection(
    title: String,
    exercises: List<Exercise>,
    onCardClicked: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Header2(
            modifier = Modifier.padding(horizontal = HorizontalMargin, vertical = Basic2),
            text = title
        )
        when (exercises.isEmpty()) {
            false -> exercises.forEach {
                ExerciseCard(
                    exerciseType = it.type,
                    exerciseName = it.name,
                    onCardClicked = { onCardClicked(it.id) }
                )
            }
            true -> {
                ExerciseCard(
                    exerciseType = null,
                    exerciseName = stringResource(id = R.string.empty_favorites_exercises_description),
                    onCardClicked = {}
                )
                ExerciseCard(
                    exerciseType = null,
                    exerciseName = stringResource(id = R.string.empty_favorites_exercises_description),
                    onCardClicked = {}
                )
                ExerciseCard(
                    exerciseType = null,
                    exerciseName = stringResource(id = R.string.empty_favorites_exercises_description),
                    onCardClicked = {}
                )
                ExerciseCard(
                    exerciseType = null,
                    exerciseName = stringResource(id = R.string.empty_favorites_exercises_description),
                    onCardClicked = {}
                )
                ExerciseCard(
                    exerciseType = null,
                    exerciseName = stringResource(id = R.string.empty_favorites_exercises_description),
                    onCardClicked = {}
                )
                ExerciseCard(
                    exerciseType = null,
                    exerciseName = stringResource(id = R.string.empty_favorites_exercises_description),
                    onCardClicked = {}
                )
                ExerciseCard(
                    exerciseType = null,
                    exerciseName = stringResource(id = R.string.empty_favorites_exercises_description),
                    onCardClicked = {}
                )
                ExerciseCard(
                    exerciseType = null,
                    exerciseName = stringResource(id = R.string.empty_favorites_exercises_description),
                    onCardClicked = {}
                )
            }
        }
    }
}