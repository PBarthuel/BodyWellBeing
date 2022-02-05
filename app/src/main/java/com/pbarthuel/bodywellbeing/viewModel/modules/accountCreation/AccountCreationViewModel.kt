package com.pbarthuel.bodywellbeing.viewModel.modules.accountCreation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pbarthuel.bodywellbeing.app.models.User
import com.pbarthuel.bodywellbeing.domain.repositories.local.dataStore.PreferenceDataStoreRepository
import com.pbarthuel.bodywellbeing.domain.repositories.local.room.user.UserRepository
import com.pbarthuel.bodywellbeing.domain.repositories.network.RealTimeDatabaseRepository
import com.pbarthuel.bodywellbeing.viewModel.utils.CoroutineToolsProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@HiltViewModel
class AccountCreationViewModel @Inject constructor(
    private val dispatcher: CoroutineToolsProvider,
    private val realTimeDatabaseRepository: RealTimeDatabaseRepository,
    private val preferenceDataStoreRepository: PreferenceDataStoreRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    var firstName: MutableState<String> = mutableStateOf("")
    var lastName: MutableState<String> = mutableStateOf("")
    var age: MutableState<String> = mutableStateOf("")
    var height: MutableState<String> = mutableStateOf("")
    var weight: MutableState<String> = mutableStateOf("")

    fun logOut() {
        viewModelScope.launch(dispatcher.io) {
            preferenceDataStoreRepository.clearDataStore()
            userRepository.clearUserDb()
        }
    }

    fun createUser() {
        viewModelScope.launch(dispatcher.io) {
            userRepository.getUser().first().let {
                realTimeDatabaseRepository.updateUserFromAccountCreation(
                    User(
                        uid = it.uid,
                        email = it.email,
                        firstName = firstName.value,
                        lastName = lastName.value,
                        age = age.value.toInt(),
                        height = height.value.toInt(),
                        weight = weight.value.toDouble(),
                        alreadyCreated = true
                    )
                )
                userRepository.updateUser(
                    User(
                        uid = it.uid,
                        email = it.email,
                        firstName = firstName.value,
                        lastName = lastName.value,
                        age = age.value.toInt(),
                        height = height.value.toInt(),
                        weight = weight.value.toDouble(),
                        alreadyCreated = true
                    )
                )
            }
        }
    }
}