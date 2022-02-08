package com.pbarthuel.bodywellbeing.app.modules.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.pbarthuel.bodywellbeing.R
import com.pbarthuel.bodywellbeing.app.models.User
import com.pbarthuel.bodywellbeing.app.ui.component.card.CustomCard
import com.pbarthuel.bodywellbeing.app.ui.component.card.ExerciseCard
import com.pbarthuel.bodywellbeing.app.ui.component.card.ProfileDetailCard
import com.pbarthuel.bodywellbeing.app.ui.component.text.Header2
import com.pbarthuel.bodywellbeing.app.ui.component.text.Header4
import com.pbarthuel.bodywellbeing.app.ui.theme.Basic2
import com.pbarthuel.bodywellbeing.app.ui.theme.HorizontalMargin
import com.pbarthuel.bodywellbeing.app.ui.theme.VerticalMargin
import com.pbarthuel.bodywellbeing.viewModel.modules.profile.ProfileScreenViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileScreenViewModel
) {
    val user by viewModel.user.collectAsState(initial = User())
    val favoritesExercises by viewModel.favoritesExercises.collectAsState(initial = listOf())
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(vertical = VerticalMargin)
    ) {
        ProfileDetailCard(
            drawableId = R.drawable.ic_launcher_background,
            displayName = "${user.firstName} ${user.lastName}",
            info = "${user.age}ans, ${user.height}cm, ${user.weight}kg "
        )
        Header2(
            modifier = Modifier.padding(horizontal = HorizontalMargin, vertical = Basic2),
            text = stringResource(id = R.string.favorites_exercises)
        )
        when (favoritesExercises.isNotEmpty()) {
            true -> favoritesExercises.forEach {
                ExerciseCard(
                    exerciseType = it.type,
                    exerciseName = it.name,
                    onCardClicked = {
                        it.id // TODO rediriger vers l'écran de détail de l'exercice avec l'id (voir si je fais un objet condensé pour les exercice)
                    }
                )
            }
            false -> {
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