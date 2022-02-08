package com.pbarthuel.bodywellbeing.viewModel.modules.exercises

import androidx.lifecycle.ViewModel
import com.pbarthuel.bodywellbeing.viewModel.utils.CoroutineToolsProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExercisesViewModel @Inject constructor(
    private val dispatcher: CoroutineToolsProvider
): ViewModel() {

}