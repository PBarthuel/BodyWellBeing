package com.pbarthuel.bodywellbeing.viewModel.modules.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pbarthuel.bodywellbeing.domain.repositories.local.dataStore.PreferenceDataStoreRepository
import com.pbarthuel.bodywellbeing.viewModel.utils.CoroutineToolsProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class MainScreenState {
    object Home: MainScreenState()
    object Body: MainScreenState()
    object Profile: MainScreenState()
}

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dispatcher: CoroutineToolsProvider,
    private val preferenceDataStoreRepository: PreferenceDataStoreRepository
) : ViewModel() {

    private val _screenState: MutableStateFlow<MainScreenState> = MutableStateFlow(MainScreenState.Home)
    val screenState: StateFlow<MainScreenState> = _screenState

    fun onScreenChanged(screenState: MainScreenState) {
        _screenState.value = screenState
    }

    fun logOut() {
        viewModelScope.launch(dispatcher.io) {
            preferenceDataStoreRepository.clearDataStore()
        }
    }
}