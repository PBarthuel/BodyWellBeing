package com.pbarthuel.bodywellbeing.app.modules.createExercise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.google.accompanist.insets.ProvideWindowInsets
import com.pbarthuel.bodywellbeing.app.models.CondenseExercise
import com.pbarthuel.bodywellbeing.app.ui.component.button.ButtonFill
import com.pbarthuel.bodywellbeing.app.ui.component.card.ExerciseCard
import com.pbarthuel.bodywellbeing.app.ui.theme.BodyWellBeingTheme
import com.pbarthuel.bodywellbeing.viewModel.modules.createExercise.CreateExerciseViewModel
import com.pbarthuel.bodywellbeing.viewModel.modules.settings.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateExerciseActivity : ComponentActivity() {

    private val viewModel by viewModels<CreateExerciseViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val exercise = remember { viewModel.getExercise("developpéCouché1") }
            BodyWellBeingTheme {
                ProvideWindowInsets {
                    Scaffold(modifier = Modifier.fillMaxSize()) {
                        Column {
                            // TODO changer ça et creer des objet firebase avec des constructeurs par default
                            //  + faire des objets UI/Domain/Data
                            ExerciseCard(exercise = CondenseExercise(
                                exercise.id,
                                exercise.name,
                                isFavorite = exercise.isFavorite,
                                exercise.type
                            )) {

                            }
                            ButtonFill(text = "Patate") {
                                viewModel.createExercise()
                            }
                        }
                    }
                }
            }
        }
    }
}