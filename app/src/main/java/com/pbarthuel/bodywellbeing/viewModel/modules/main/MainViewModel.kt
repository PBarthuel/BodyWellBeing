package com.pbarthuel.bodywellbeing.viewModel.modules.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.pbarthuel.bodywellbeing.app.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed class State {
    object Loading: State()
    data class Error(val errorMessage: String): State()
    data class Success(val data: Any): State()
}

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {

    private val _state: MutableLiveData<State?> = MutableLiveData(null)
    val state: LiveData<State?> = _state

    fun logUser(
        email: String,
        password: String,
        auth: FirebaseAuth
    ) {
        when {
            email.isEmpty() -> { _state.value = State.Error("No email") }
            password.isEmpty() -> { _state.value = State.Error("No password") }
            else -> {
                _state.value = State.Loading
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Login", "signInWithEmail:success")
                            task.result.user?.let {
                                if (it.email != null) {
                                    _state.value = State.Success(
                                        User(it.email!!, it.displayName)
                                    )
                                }
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Login", "signInWithEmail:failure", task.exception)
                            _state.value = State.Error("Authentication failed.")
                        }
                    }
            }
        }
    }

    fun createAccount(auth: FirebaseAuth, email: String, password: String, confirmedPassword: String) {
        _state.value = State.Loading
        if (password == confirmedPassword) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    when (task.isSuccessful) {
                        true -> {
                            Log.d("CreateAccount", "createUserWithEmail:success")
                            task.result.user?.let {
                                if (it.email != null) {
                                    _state.value = State.Success(
                                        User(it.email!!, it.displayName)
                                    )
                                }
                            }
                        }
                        else -> {
                            Log.w("CreateAccount", "createUserWithEmail:failure", task.exception)
                            _state.value = State.Error("Something went wrong during your account creation")
                        }
                    }
                }
        } else { _state.value = State.Error("Passwords don't match !") }
    }

    fun loginWithGoogle(task: Task<GoogleSignInAccount>?) {
        _state.value = State.Loading
        try {
            val account = task?.getResult(ApiException::class.java)
            if (account == null) {
                _state.value = State.Error("Something went wrong (google)")
            } else {
                _state.value = State.Success(User(account.email ?: "", account.displayName))
            }
        } catch (e: ApiException) {
            _state.value = State.Error("Something went wrong (google)")
        }
    }

    fun loginSuccess() {
        _state.value = null
    }

    fun isAlreadyLog(auth: FirebaseAuth) {
        auth.currentUser?.let {
            if (it.email != null) {
                _state.value = State.Success(
                    User(it.email!!, it.displayName)
                )
            }
        }
    }
}