package com.pbarthuel.bodywellbeing.app.modules.createExercise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.google.accompanist.insets.ProvideWindowInsets
import com.pbarthuel.bodywellbeing.R
import com.pbarthuel.bodywellbeing.app.ui.component.button.ButtonFill
import com.pbarthuel.bodywellbeing.app.ui.component.input.DropdownMenuItem
import com.pbarthuel.bodywellbeing.app.ui.component.input.TextFieldWithDropdownMenu
import com.pbarthuel.bodywellbeing.app.ui.component.text.Header3
import com.pbarthuel.bodywellbeing.app.ui.theme.BodyWellBeingTheme
import com.pbarthuel.bodywellbeing.app.ui.theme.HorizontalMargin
import com.pbarthuel.bodywellbeing.data.constants.ExercisesConstants
import com.pbarthuel.bodywellbeing.viewModel.modules.createExercise.CreateExerciseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateExerciseActivity : ComponentActivity() {

    private val viewModel by viewModels<CreateExerciseViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BodyWellBeingTheme {
                ProvideWindowInsets {
                    val isUserAdmin = viewModel.isUserAdmin().collectAsState(initial = false)
                    Scaffold(modifier = Modifier.fillMaxSize()) {
                        val items = listOf(
                            DropdownMenuItem(ExercisesConstants.ARM_EXERCISE_TYPE, stringResource(id = R.string.arm_exercises)),
                            DropdownMenuItem(ExercisesConstants.TRICEPS_EXERCISE_TYPE, stringResource(id = R.string.triceps_exercises)),
                            DropdownMenuItem(ExercisesConstants.BACK_EXERCISE_TYPE, stringResource(id = R.string.back_exercises)),
                            DropdownMenuItem(ExercisesConstants.SHOULDER_EXERCISE_TYPE, stringResource(id = R.string.shoulder_exercises)),
                            DropdownMenuItem(ExercisesConstants.CHEST_EXERCISE_TYPE, stringResource(id = R.string.chest_exercises)),
                            DropdownMenuItem(ExercisesConstants.ABS_EXERCISE_TYPE, stringResource(id = R.string.abs_exercises)),
                            DropdownMenuItem(ExercisesConstants.LEG_EXERCISE_TYPE, stringResource(id = R.string.leg_exercises)),
                        )
                        LazyColumn(modifier = Modifier
                            .fillMaxSize()
                            .padding(HorizontalMargin)) {
                            if (isUserAdmin.value == true) {
                                item {
                                    Header3(text = "admin")
                                }
                            }
                            item {
                                TextFieldWithDropdownMenu(items = items) { exerciseType ->

                                }
                            }
                            item {
                                ButtonFill(text = "CreateExercise") {
                                    viewModel.createExercise()
                                }
                                ButtonFill(text = "CreateCustomExercise") {
                                    viewModel.createCustomExercise()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}