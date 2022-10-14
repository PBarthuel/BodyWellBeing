package com.pbarthuel.bodywellbeing.viewModel.modules.exercises

import androidx.lifecycle.ViewModel
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.articles.RoomArticlesRepository
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.exercises.RoomCustomExercisesRepository
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.exercises.RoomExercisesRepository
import com.pbarthuel.bodywellbeing.viewModel.utils.CoroutineToolsProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn

@HiltViewModel
class InfosViewModel @Inject constructor(
    private val dispatcher: CoroutineToolsProvider,
    private val roomExercisesRepository: RoomExercisesRepository,
    private val roomCustomExercisesRepository: RoomCustomExercisesRepository,
    private val roomArticlesRepository: RoomArticlesRepository
): ViewModel() {

    val exercisesGroupByType = combine(
        roomExercisesRepository.getAllCondenseExercises(),
        roomCustomExercisesRepository.getAllCondenseExercises()
    ) { exercises, customExercises -> (exercises + customExercises).groupBy { it.type } }.flowOn(dispatcher.io)

    val articles = roomArticlesRepository.getAllArticles()
}