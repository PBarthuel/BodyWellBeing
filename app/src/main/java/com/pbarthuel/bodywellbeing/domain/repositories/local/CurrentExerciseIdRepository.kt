package com.pbarthuel.bodywellbeing.domain.repositories.local

import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Singleton
class CurrentExerciseIdRepository @Inject constructor() {

    private val _exerciseId: MutableStateFlow<String?> = MutableStateFlow(null)
    val exerciseId: StateFlow<String?> = _exerciseId

    fun setExerciseId(exerciseId: String) {
        _exerciseId.value = exerciseId
    }
}