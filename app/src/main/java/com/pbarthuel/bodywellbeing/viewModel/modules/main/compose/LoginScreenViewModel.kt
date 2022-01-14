package com.pbarthuel.bodywellbeing.viewModel.modules.main.compose

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.pbarthuel.bodywellbeing.app.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor() : ViewModel() {
    private val _user: MutableStateFlow<User?> = MutableStateFlow(null)
    val user: StateFlow<User?> = _user

    private val _emailLoginButtonState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val emailLoginButtonState: StateFlow<Boolean> = _emailLoginButtonState

    fun signIn(user: FirebaseUser) {
        if (user.email != null && user.displayName != null) {
            _user.value = User(user.email!!, user.displayName!!)
        }
    }

    fun signInFail() {
        _emailLoginButtonState.value = false
    }

    fun loading() {
        _emailLoginButtonState.value = true
    }
}