package com.pbarthuel.bodywellbeing.viewModel.modules.main.compose

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.pbarthuel.bodywellbeing.app.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class CreateAccountScreenViewModel @Inject constructor(): ViewModel() {
    private val _user: MutableStateFlow<User?> = MutableStateFlow(null)
    val user: StateFlow<User?> = _user

    private val _accountCreationButtonState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val accountCreationButtonState: StateFlow<Boolean> = _accountCreationButtonState

    fun createAccount(user: FirebaseUser) {
        if (user.email != null && user.displayName != null) {
            _user.value = User(user.email!!, user.displayName!!)
        }
    }

    fun signInFail() {
        _accountCreationButtonState.value = false
    }

    fun loading() {
        _accountCreationButtonState.value = true
    }
}