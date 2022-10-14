package com.pbarthuel.bodywellbeing.app.modules.infos

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.pbarthuel.bodywellbeing.app.model.CondenseExercise
import com.pbarthuel.bodywellbeing.app.model.article.Article
import com.pbarthuel.bodywellbeing.app.ui.component.SegmentedControl
import com.pbarthuel.bodywellbeing.app.ui.component.card.ArticleCard
import com.pbarthuel.bodywellbeing.app.ui.component.card.ExercisesCardSection
import com.pbarthuel.bodywellbeing.app.ui.theme.VerticalMargin
import com.pbarthuel.bodywellbeing.viewModel.modules.exercises.InfosViewModel

private const val EXERCISE_INDEX = 0
private const val ARTICLE_INDEX = 1

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InfosScreen(
    viewModel: InfosViewModel = hiltViewModel(),
    onExerciseCardClicked: (CondenseExercise) -> Unit,
    onArticleCardClicked: (Article) -> Unit,
) {
    val exercisesGroupByType by viewModel.exercisesGroupByType.collectAsState(mapOf())
    val articles by viewModel.articles.collectAsState(listOf())
    var selectedSection by remember { mutableStateOf(EXERCISE_INDEX) }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(VerticalMargin)
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
                        articles.forEach {
                            ArticleCard(article = it) {
                                onArticleCardClicked(it)
                            }
                        }
                    }
                }
            }
        }
    }
}