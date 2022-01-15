package com.pbarthuel.bodywellbeing.viewModel.modules.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pbarthuel.bodywellbeing.domain.repositories.local.PreferenceDataStoreRepository
import com.pbarthuel.bodywellbeing.viewModel.utils.CoroutineToolsProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dispatcher: CoroutineToolsProvider,
    private val preferenceDataStoreRepository: PreferenceDataStoreRepository
) : ViewModel() {

    val id: Flow<String?> = flow {
        preferenceDataStoreRepository.getFromDataStore().collect {
            emit(it)
        }
    }

    fun logOut() {
        viewModelScope.launch(dispatcher.io) {
            preferenceDataStoreRepository.clearDataStore()
        }
    }
}