package com.pbarthuel.bodywellbeing.app.ui.component.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.pbarthuel.bodywellbeing.R
import com.pbarthuel.bodywellbeing.app.models.CondenseExercise
import com.pbarthuel.bodywellbeing.app.models.Exercise
import com.pbarthuel.bodywellbeing.app.ui.component.text.Header2
import com.pbarthuel.bodywellbeing.app.ui.component.text.Header3
import com.pbarthuel.bodywellbeing.app.ui.theme.Basic2
import com.pbarthuel.bodywellbeing.app.ui.theme.BodyWellBeingTheme
import com.pbarthuel.bodywellbeing.app.ui.theme.HorizontalMargin
import com.pbarthuel.bodywellbeing.data.constants.ExercisesConstants

@Composable
fun ExercisesCardSection(
    title: String,
    exercises: List<CondenseExercise>,
    onCardClicked: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Header3(
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
                    exerciseName = stringResource(id = R.string.empty_exercises_description),
                    onCardClicked = {}
                )
            }
        }
    }
}

@Composable
fun FavoriteExercisesCardSection(
    exercises: List<CondenseExercise>,
    onCardClicked: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Header2(
            modifier = Modifier.padding(horizontal = HorizontalMargin, vertical = Basic2),
            text = stringResource(id = R.string.favorites_exercises)
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
            }
        }
    }
}

@Preview(name = "Primary Buttons", showBackground = true)
@Composable
fun PreviewExercisesCardSection() {
    BodyWellBeingTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            ExercisesCardSection(title = "Arm exercises", exercises = listOf(), onCardClicked = {})
            ExercisesCardSection(
                title = "Arm exercises",
                exercises = listOf(CondenseExercise(id = "arm1", name = "Arm", type = ExercisesConstants.ARM_EXERCISE_TYPE)),
                onCardClicked = {}
            )
        }
    }
}

@Preview(name = "Primary Buttons", showBackground = true)
@Composable
fun PreviewFavoriteExercisesCardSection() {
    BodyWellBeingTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            FavoriteExercisesCardSection(exercises = listOf(), onCardClicked = {})
            FavoriteExercisesCardSection(
                exercises = listOf(CondenseExercise(id = "arm1", name = "Arm", type = ExercisesConstants.ARM_EXERCISE_TYPE)),
                onCardClicked = {}
            )
        }
    }
}