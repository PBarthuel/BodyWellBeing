package com.pbarthuel.bodywellbeing.app.modules.exerciseDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pbarthuel.bodywellbeing.app.ui.component.button.FavoriteButton
import com.pbarthuel.bodywellbeing.app.ui.component.text.Body1
import com.pbarthuel.bodywellbeing.app.ui.component.text.Header1
import com.pbarthuel.bodywellbeing.app.ui.theme.Basic1
import com.pbarthuel.bodywellbeing.app.ui.theme.BodyWellBeingTheme
import com.pbarthuel.bodywellbeing.app.ui.theme.HorizontalMargin
import com.pbarthuel.bodywellbeing.app.ui.theme.Layout1
import com.pbarthuel.bodywellbeing.app.ui.theme.VerticalMargin
import com.pbarthuel.bodywellbeing.viewModel.modules.exerciseDetail.ExerciseDetailViewModel
import java.lang.Exception
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun ExerciseDetailScreen(
    viewModel: ExerciseDetailViewModel,
    onNavigationBackClicked: () -> Unit,
) {
    val exercise = viewModel.exercise.collectAsState(initial = null)
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(BodyWellBeingTheme.colors.actionSecondary)
    ) {
        item {
            TopAppBar(
                modifier = Modifier.padding(Basic1),
                backgroundColor = Color.Transparent.copy(alpha = 0f),
                elevation = 0.dp,
                title = { },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigationBackClicked
                    ) { Icon(Icons.Filled.ArrowBack, contentDescription = "Settings") }
                },
                actions = { }
            )
        }
        item {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = VerticalMargin, horizontal = HorizontalMargin)
            ) {
                FavoriteButton(
                    isFavorite = exercise.value?.isFavorite ?: false,
                    onFavoriteClicked = {
                        viewModel.modifyFavoriteState(
                            exerciseId = exercise.value?.id ?: throw Exception("During modifyFavoriteState id is null"),
                            isFavorite = exercise.value?.isFavorite ?: throw Exception("During modifyFavoriteState isFavorite is null")
                        )
                    }
                )
                Header1(modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(bottom = Layout1), text = exercise.value?.name ?: "error")
                Body1(text = exercise.value?.description ?: "error")
            }
        }
    }
}