package com.pbarthuel.bodywellbeing.viewModel.modules.main.compose

import androidx.lifecycle.ViewModel
import com.pbarthuel.bodywellbeing.app.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class GoogleAuthViewModel @Inject constructor(): ViewModel() {
    private val _user: MutableStateFlow<User?> = MutableStateFlow(null)
    val user: StateFlow<User?> = _user

    private val _googleLoginButtonState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val googleLoginButtonState: StateFlow<Boolean> = _googleLoginButtonState

    fun signIn(email: String, displayName: String) {
        _user.value = User(email, displayName)
        _googleLoginButtonState.value = false
    }

    fun signInFail() {
        _googleLoginButtonState.value = false
    }

    fun loading() {
        _googleLoginButtonState.value = true
    }
}