package com.pbarthuel.bodywellbeing.viewModel.modules.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.pbarthuel.bodywellbeing.domain.repositories.local.PreferenceDataStoreRepository
import com.pbarthuel.bodywellbeing.domain.repositories.network.RealTimeDatabaseRepository
import com.pbarthuel.bodywellbeing.viewModel.utils.CoroutineToolsProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

sealed class LoginState {
    object Loading: LoginState()
    data class Error(val errorMessage: String): LoginState()
    object Login: LoginState()
    object CreateAccount: LoginState()
}

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val dispatcher: CoroutineToolsProvider,
    private val preferenceDataStoreRepository: PreferenceDataStoreRepository,
    private val realTimeDatabaseRepository: RealTimeDatabaseRepository
): ViewModel() {

    private val _state: MutableStateFlow<LoginState?> = MutableStateFlow(null)
    val state: Flow<LoginState?> = _state

    fun logUser(
        email: String,
        password: String,
        auth: FirebaseAuth
    ) {
        when {
            email.isEmpty() -> { _state.value = LoginState.Error("No email")
            }
            password.isEmpty() -> { _state.value = LoginState.Error("No password")
            }
            else -> {
                _state.value = LoginState.Loading
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Login", "signInWithEmail:success")
                            task.result.user?.let { fireBaseUser ->
                                viewModelScope.launch(dispatcher.io) {
                                    realTimeDatabaseRepository.getUser(
                                        fireBaseUser.uid, fireBaseUser.email ?: throw Exception("email is null")
                                    )
                                }
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Login", "signInWithEmail:failure", task.exception)
                            _state.value = LoginState.Error("Authentication failed.")
                        }
                    }
            }
        }
    }

    fun createAccount(auth: FirebaseAuth, email: String, password: String, confirmedPassword: String) {
        _state.value = LoginState.Loading
        if (password == confirmedPassword) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    when (task.isSuccessful) {
                        true -> {
                            Log.d("CreateAccount", "createUserWithEmail:success")
                            task.result.user?.let { fireBaseUser ->
                                viewModelScope.launch(dispatcher.io) {
                                    realTimeDatabaseRepository.getUser(
                                        fireBaseUser.uid, fireBaseUser.email ?: throw Exception("email is null")
                                    )
                                }
                            }
                        }
                        else -> {
                            Log.w("CreateAccount", "createUserWithEmail:failure", task.exception)
                            _state.value =
                                LoginState.Error("Something went wrong during your account creation")
                        }
                    }
                }
        } else { _state.value = LoginState.Error("Passwords don't match !")
        }
    }

    fun loginWithGoogle(task: Task<GoogleSignInAccount>?) {
        viewModelScope.launch {
            _state.value = LoginState.Loading
            kotlin.runCatching {
                task?.getResult(ApiException::class.java)
            }.onSuccess { account ->
                if (account == null) {
                    _state.value = LoginState.Error("Something went wrong (google)")
                } else {
                    viewModelScope.launch(dispatcher.io) {
                        realTimeDatabaseRepository.getUser(
                            account.id ?: throw Exception("uid is null"), account.email ?: throw Exception("email is null")
                        )
                    }
                }
            }.onFailure { _state.value = LoginState.Error("Something went wrong (google)") }
        }
    }

    fun loginSuccess(auth: FirebaseAuth) {
        _state.value = null
        auth.signOut()
    }

    fun isAlreadyLog() {
        viewModelScope.launch(dispatcher.main) {
            preferenceDataStoreRepository.isUserConnected().collect {
                when (it) {
                    is LoginState.CreateAccount -> _state.value = LoginState.CreateAccount
                    is LoginState.Login -> _state.value = LoginState.Login
                    else -> _state.value = null
                }
            }
        }
    }
}