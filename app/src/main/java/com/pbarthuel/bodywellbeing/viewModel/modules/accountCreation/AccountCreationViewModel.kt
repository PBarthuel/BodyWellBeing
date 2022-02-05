package com.pbarthuel.bodywellbeing.viewModel.modules.accountCreation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pbarthuel.bodywellbeing.domain.repositories.local.dataStore.PreferenceDataStoreRepository
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.user.UserRepository
import com.pbarthuel.bodywellbeing.viewModel.utils.CoroutineToolsProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class AccountCreationViewModel @Inject constructor(
    private val dispatcher: CoroutineToolsProvider,
    private val preferenceDataStoreRepository: PreferenceDataStoreRepository,
    private val userRepository: UserRepository
): ViewModel() {

    var firstName: MutableState<String> = mutableStateOf("")
    var lastName: MutableState<String> = mutableStateOf("")
    var age: MutableState<Int> = mutableStateOf(0)
    var height: MutableState<Int> = mutableStateOf(0)
    var weight: MutableState<Double> = mutableStateOf(0.0)

    fun logOut() {
        viewModelScope.launch(dispatcher.io) {
            preferenceDataStoreRepository.clearDataStore()
            userRepository.clearUserDb()
        }
    }
}