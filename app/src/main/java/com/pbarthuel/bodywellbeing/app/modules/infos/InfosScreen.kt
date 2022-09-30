package com.pbarthuel.bodywellbeing.app.modules.infos

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.pbarthuel.bodywellbeing.app.models.CondenseExercise
import com.pbarthuel.bodywellbeing.app.ui.component.SegmentedControl
import com.pbarthuel.bodywellbeing.app.ui.component.StepGoalGauge
import com.pbarthuel.bodywellbeing.app.ui.component.card.ExercisesCardSection
import com.pbarthuel.bodywellbeing.app.ui.theme.Basic3
import com.pbarthuel.bodywellbeing.viewModel.modules.exercises.ExercisesViewModel

private const val EXERCISE_INDEX = 0
private const val ARTICLE_INDEX = 1

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InfosScreen(
    viewModel: ExercisesViewModel = hiltViewModel(),
    onExerciseCardClicked: (CondenseExercise) -> Unit
) {
    val exercisesGroupByType by viewModel.exercisesGroupByType.collectAsState(mapOf())
    var selectedSection by remember { mutableStateOf(EXERCISE_INDEX) }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(Basic3)
    ) {
        stickyHeader {
            SegmentedControl(
                entry = listOf(
                    "Exercise",
                    "Article"
                ),
                selectedItemIndex = selectedSection,
                onItemSelected = { selectedSection = it }
            )
        }

        item {
            Crossfade(targetState = selectedSection) { index ->
                when (index) {
                    EXERCISE_INDEX -> Column {
                        exercisesGroupByType.forEach { (_, exercises) ->
                            ExercisesCardSection(
                                exercises = exercises,
                                onCardClicked = { exercise ->
                                    onExerciseCardClicked(exercise)
                                }
                            )
                        }
                    }
                    ARTICLE_INDEX -> Column {
                        StepGoalGauge(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .clickable(
                                    interactionSource = MutableInteractionSource(),
                                    indication = null
                                ) {

                                },
                            progress = 0.7f,
                            textInside = "",
                            title = "Click here to grant permission",
                            animate = true
                        )
                    }
                }
            }
        }
    }
}