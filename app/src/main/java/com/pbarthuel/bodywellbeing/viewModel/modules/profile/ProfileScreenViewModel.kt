package com.pbarthuel.bodywellbeing.viewModel.modules.profile

import androidx.lifecycle.ViewModel
import com.pbarthuel.bodywellbeing.app.models.User
import com.pbarthuel.bodywellbeing.domain.repositories.local.PreferenceDataStoreRepository
import com.pbarthuel.bodywellbeing.viewModel.utils.CoroutineToolsProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val dispatcher: CoroutineToolsProvider,
    private val preferenceDataStoreRepository: PreferenceDataStoreRepository
): ViewModel() {

    val user: Flow<User> = preferenceDataStoreRepository.getUser()

}