package com.pbarthuel.bodywellbeing.viewModel.modules.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pbarthuel.bodywellbeing.app.models.User
import com.pbarthuel.bodywellbeing.domain.repositories.local.PreferenceDataStoreRepository
import com.pbarthuel.bodywellbeing.domain.repositories.network.RealTimeDatabaseRepository
import com.pbarthuel.bodywellbeing.viewModel.utils.CoroutineToolsProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.switchMap
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dispatcher: CoroutineToolsProvider,
    private val preferenceDataStoreRepository: PreferenceDataStoreRepository
) : ViewModel() {

    val id: String? = runBlocking { preferenceDataStoreRepository.getUserId() }

    val email: String? = runBlocking { preferenceDataStoreRepository.getUserEmail() }

    val user: MutableStateFlow<User?> = MutableStateFlow(User())

    fun logOut() {
        viewModelScope.launch(dispatcher.io) {
            preferenceDataStoreRepository.clearDataStore()
        }
    }

    fun getUser() {
        viewModelScope.launch {
            user.value = User(
                uid = preferenceDataStoreRepository.getUserId() ?: throw Exception("uid is null"),
                email = preferenceDataStoreRepository.getUserEmail() ?: throw Exception("email is null")
            )
        }
    }
}