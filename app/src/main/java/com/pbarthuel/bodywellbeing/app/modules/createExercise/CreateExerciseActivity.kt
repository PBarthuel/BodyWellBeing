package com.pbarthuel.bodywellbeing.app.modules.createExercise

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.google.accompanist.insets.ProvideWindowInsets
import com.pbarthuel.bodywellbeing.R
import com.pbarthuel.bodywellbeing.app.ui.component.button.ButtonFill
import com.pbarthuel.bodywellbeing.app.ui.component.input.DropdownMenuItem
import com.pbarthuel.bodywellbeing.app.ui.component.input.FormInputField
import com.pbarthuel.bodywellbeing.app.ui.component.input.InputFieldType
import com.pbarthuel.bodywellbeing.app.ui.component.input.TextFieldWithDropdownMenu
import com.pbarthuel.bodywellbeing.app.ui.component.text.Body1
import com.pbarthuel.bodywellbeing.app.ui.theme.BodyWellBeingTheme
import com.pbarthuel.bodywellbeing.app.ui.theme.HorizontalMargin
import com.pbarthuel.bodywellbeing.app.ui.theme.VerticalMargin
import com.pbarthuel.bodywellbeing.data.constants.ExercisesConstants
import com.pbarthuel.bodywellbeing.viewModel.modules.createExercise.CreateExerciseState
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
                    var switchState by remember { mutableStateOf(false) }
                    Scaffold(modifier = Modifier.fillMaxSize()) {
                        val exerciseTypeItems = listOf(
                            DropdownMenuItem(ExercisesConstants.ARM_EXERCISE_TYPE, stringResource(id = R.string.arm_exercises)),
                            DropdownMenuItem(ExercisesConstants.TRICEPS_EXERCISE_TYPE, stringResource(id = R.string.triceps_exercises)),
                            DropdownMenuItem(ExercisesConstants.BACK_EXERCISE_TYPE, stringResource(id = R.string.back_exercises)),
                            DropdownMenuItem(ExercisesConstants.SHOULDER_EXERCISE_TYPE, stringResource(id = R.string.shoulder_exercises)),
                            DropdownMenuItem(ExercisesConstants.CHEST_EXERCISE_TYPE, stringResource(id = R.string.chest_exercises)),
                            DropdownMenuItem(ExercisesConstants.ABS_EXERCISE_TYPE, stringResource(id = R.string.abs_exercises)),
                            DropdownMenuItem(ExercisesConstants.LEG_EXERCISE_TYPE, stringResource(id = R.string.leg_exercises)),
                        )
                        Box(modifier = Modifier.fillMaxSize()) {
                            when (viewModel.state.value is CreateExerciseState.LOADING) {
                                false -> Column(modifier = Modifier.fillMaxSize()) {
                                    LazyColumn(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .weight(1f)
                                            .padding(HorizontalMargin)
                                    ) {
                                        if (isUserAdmin.value == true) {
                                            item {
                                                Box(modifier = Modifier.fillMaxWidth()) {
                                                    Body1(
                                                        modifier = Modifier.align(Alignment.CenterStart),
                                                        text = "Create classic exercise"
                                                    )
                                                    Switch(
                                                        modifier = Modifier.align(Alignment.CenterEnd),
                                                        checked = switchState,
                                                        onCheckedChange = { switchState = !switchState }
                                                    )
                                                }
                                            }
                                        }
                                        item {
                                            TextFieldWithDropdownMenu(
                                                modifier = Modifier.padding(vertical = VerticalMargin),
                                                items = exerciseTypeItems
                                            ) { exerciseType ->
                                                viewModel.exerciseType.value = exerciseType
                                            }
                                            FormInputField(
                                                modifier = Modifier
                                                    .fillMaxWidth(),
                                                label = "Exercise name",
                                                type = InputFieldType.Text,
                                                text = viewModel.name.value,
                                                placeHolder = "Développé couché",
                                                onValueChange = { viewModel.name.value = it }
                                            )
                                            FormInputField(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(vertical = VerticalMargin),
                                                label = "ImageUrl",
                                                type = InputFieldType.Text,
                                                text = viewModel.imageUrl.value,
                                                placeHolder = "https://image.fr",
                                                onValueChange = { viewModel.imageUrl.value = it }
                                            )
                                            FormInputField(
                                                modifier = Modifier
                                                    .fillMaxWidth(),
                                                label = "Description",
                                                type = InputFieldType.Text,
                                                text = viewModel.description.value,
                                                placeHolder = "Description",
                                                onValueChange = { viewModel.description.value = it }
                                            )
                                        }
                                    }
                                    ButtonFill(
                                        text = "Create",
                                        enabled = viewModel.name.value.isNotEmpty()
                                                && viewModel.exerciseType.value != null
                                                && viewModel.description.value.isNotEmpty()
                                    ) {
                                        viewModel.createExercise(switchState)
                                    }
                                }
                                true -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                            }
                        }
                    }
                    when (val state = viewModel.state.value) {
                        CreateExerciseState.SUCCESS -> finish()
                        is CreateExerciseState.FAIL -> Toast.makeText(this, state.error.message, Toast.LENGTH_SHORT).show()
                        else -> {}
                    }
                }
            }
        }
    }
}