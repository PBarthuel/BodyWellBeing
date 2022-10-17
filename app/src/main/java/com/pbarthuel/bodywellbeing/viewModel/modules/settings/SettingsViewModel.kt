package com.pbarthuel.bodywellbeing.viewModel.modules.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pbarthuel.bodywellbeing.domain.repositories.local.dataStore.PreferenceDataStoreRepository
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.articles.RoomArticlesRepository
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.exercises.RoomCustomExercisesRepository
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.exercises.RoomExercisesRepository
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.programs.RoomProgramsRepository
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.user.RoomUserRepository
import com.pbarthuel.bodywellbeing.viewModel.utils.CoroutineToolsProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class SettingsScreenState {
    object Home: SettingsScreenState()
    object Logout: SettingsScreenState()
    object Loading: SettingsScreenState()
}

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val preferenceDataStoreRepository: PreferenceDataStoreRepository,
    private val roomExercisesRepository: RoomExercisesRepository,
    private val roomUserRepository: RoomUserRepository,
    private val roomCustomExercisesRepository: RoomCustomExercisesRepository,
    private val roomProgramsRepository: RoomProgramsRepository,
    private val roomArticlesRepository: RoomArticlesRepository,
    private val dispatcher: CoroutineToolsProvider
) : ViewModel() {

    private val _screenState: MutableStateFlow<SettingsScreenState> = MutableStateFlow(SettingsScreenState.Home)
    val screenState: StateFlow<SettingsScreenState> = _screenState

    fun logOut() {
        viewModelScope.launch(dispatcher.io) {
            kotlin.runCatching {
                _screenState.value = SettingsScreenState.Loading
                roomExercisesRepository.resetAllIsFavoriteAtLogout()
                roomCustomExercisesRepository.clearExercisesDb()
                roomProgramsRepository.clearProgramsDb()
                roomArticlesRepository.clearArticlesDb()
                roomUserRepository.clearUserDb()
                preferenceDataStoreRepository.clearDataStore()
            }.onSuccess {
                _screenState.value = SettingsScreenState.Logout
            }
        }
    }
}