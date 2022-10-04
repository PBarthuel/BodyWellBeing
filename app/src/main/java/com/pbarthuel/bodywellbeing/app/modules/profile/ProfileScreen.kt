package com.pbarthuel.bodywellbeing.app.modules.profile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.pbarthuel.bodywellbeing.R
import com.pbarthuel.bodywellbeing.app.model.CondenseExercise
import com.pbarthuel.bodywellbeing.app.model.User
import com.pbarthuel.bodywellbeing.app.ui.component.card.FavoriteExercisesCardSection
import com.pbarthuel.bodywellbeing.app.ui.component.card.ProfileDetailCard
import com.pbarthuel.bodywellbeing.app.ui.theme.VerticalMargin
import com.pbarthuel.bodywellbeing.viewModel.modules.profile.ProfileScreenViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileScreenViewModel,
    onExerciseCardClicked: (CondenseExercise) -> Unit
) {
    val user by viewModel.user.collectAsState(initial = User())
    val favoritesExercises by viewModel.favoritesExercises.collectAsState(initial = listOf())
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = VerticalMargin)
    ) {
        item {
            ProfileDetailCard(
                drawableId = R.drawable.ic_launcher_background,
                displayName = "${user.firstName} ${user.lastName}",
                info = "${user.age}ans, ${user.height}cm, ${user.weight}kg "
            )
        }
        item {
            FavoriteExercisesCardSection(
                exercises = favoritesExercises,
                onCardClicked = { exercise -> onExerciseCardClicked(exercise) },
                onNoFavoriteExerciseCardClicked = {
                    // TODO redirection vers l'écran des exercices
                }
            )
        }
    }
}