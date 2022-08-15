package com.pbarthuel.bodywellbeing.viewModel.modules.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pbarthuel.bodywellbeing.domain.repositories.local.dataStore.PreferenceDataStoreRepository
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.exercises.RoomCustomExercisesRepository
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.exercises.RoomExercisesRepository
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
    private val customExercisesRepository: RoomCustomExercisesRepository,
    private val dispatcher: CoroutineToolsProvider
) : ViewModel() {

    private val _screenState: MutableStateFlow<SettingsScreenState> = MutableStateFlow(SettingsScreenState.Home)
    val screenState: StateFlow<SettingsScreenState> = _screenState

    fun logOut() {
        viewModelScope.launch(dispatcher.io) {
            kotlin.runCatching {
                _screenState.value = SettingsScreenState.Loading
                roomExercisesRepository.resetAllIsFavoriteAtLogout()
                customExercisesRepository.clearExercisesDb()
                preferenceDataStoreRepository.clearDataStore()
                roomUserRepository.clearUserDb()
            }.onSuccess {
                _screenState.value = SettingsScreenState.Logout
            }
        }
    }
}