package com.pbarthuel.bodywellbeing.app.modules.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.pbarthuel.bodywellbeing.R
import com.pbarthuel.bodywellbeing.app.models.User
import com.pbarthuel.bodywellbeing.app.ui.component.button.FavoriteButton
import com.pbarthuel.bodywellbeing.app.ui.component.card.FavoriteExercisesCardSection
import com.pbarthuel.bodywellbeing.app.ui.component.card.ProfileDetailCard
import com.pbarthuel.bodywellbeing.app.ui.theme.VerticalMargin
import com.pbarthuel.bodywellbeing.viewModel.modules.profile.ProfileScreenViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileScreenViewModel,
    onExerciseCardClicked: (String) -> Unit
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
        FavoriteExercisesCardSection(
            exercises = favoritesExercises,
            onCardClicked = { exerciseId -> onExerciseCardClicked(exerciseId) },
            onNoFavoriteExerciseCardClicked = {
                // TODO redirection vers l'écran des exercices
            }
        )
    }
}