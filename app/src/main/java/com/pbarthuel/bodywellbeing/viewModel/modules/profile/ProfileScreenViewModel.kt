package com.pbarthuel.bodywellbeing.viewModel.modules.profile

import androidx.lifecycle.ViewModel
import com.pbarthuel.bodywellbeing.app.models.CondenseExercise
import com.pbarthuel.bodywellbeing.app.models.User
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.exercises.RoomCustomExercisesRepository
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.exercises.RoomExercisesRepository
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.user.UserRepository
import com.pbarthuel.bodywellbeing.viewModel.utils.CoroutineToolsProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val dispatcher: CoroutineToolsProvider,
    private val userRepository: UserRepository,
    private val roomExercisesRepository: RoomExercisesRepository,
    private val roomCustomExercisesRepository: RoomCustomExercisesRepository
) : ViewModel() {

    val user: Flow<User> = userRepository.getUser().flowOn(dispatcher.io)

    val favoritesExercises: Flow<List<CondenseExercise>> = combine(
        roomExercisesRepository.getFavoritesExercises(),
        roomCustomExercisesRepository.getFavoritesExercises()
    ) { exercises, customExercises -> (exercises + customExercises) }.flowOn(dispatcher.io)

}