package com.pbarthuel.bodywellbeing.viewModel.modules.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pbarthuel.bodywellbeing.domain.repositories.local.dataStore.PreferenceDataStoreRepository
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.user.UserRepository
import com.pbarthuel.bodywellbeing.viewModel.modules.main.MainScreenState
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
    private val userRepository: UserRepository,
    private val dispatcher: CoroutineToolsProvider
) : ViewModel() {

    private val _screenState: MutableStateFlow<SettingsScreenState> = MutableStateFlow(SettingsScreenState.Home)
    val screenState: StateFlow<SettingsScreenState> = _screenState

    fun logOut() {
        viewModelScope.launch(dispatcher.io) {
            kotlin.runCatching {
                _screenState.value = SettingsScreenState.Loading
                preferenceDataStoreRepository.clearDataStore()
                userRepository.clearUserDb()
            }.onSuccess {
                _screenState.value = SettingsScreenState.Logout
            }
        }
    }
}