package com.pbarthuel.bodywellbeing.app.modules.main.compose

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.firebase.auth.FirebaseAuth
import com.pbarthuel.bodywellbeing.app.models.User
import com.pbarthuel.bodywellbeing.app.ui.component.ButtonFill
import com.pbarthuel.bodywellbeing.app.ui.component.input.FormInputField
import com.pbarthuel.bodywellbeing.app.ui.component.input.InputFieldType
import com.pbarthuel.bodywellbeing.app.ui.component.text.Eyebrow
import com.pbarthuel.bodywellbeing.app.ui.template.ButtonsBar
import com.pbarthuel.bodywellbeing.app.ui.theme.Basic2
import com.pbarthuel.bodywellbeing.app.ui.theme.Layout1
import com.pbarthuel.bodywellbeing.viewModel.modules.main.compose.LoginScreenViewModel

@ExperimentalMaterialApi
@Composable
fun LoginScreen(
    auth: FirebaseAuth,
    viewModel: LoginScreenViewModel,
    onAccountCreationClick: () -> Unit,
    onLoginWithGoogle: (User) -> Unit,
    onLoginWithEmail: (User) -> Unit
) {
    val user by remember(viewModel) { viewModel.user }.collectAsState()
    val emailLoginButtonState by remember(viewModel) { viewModel.emailLoginButtonState }.collectAsState()
    Scaffold(content = {
        var emailText by remember { mutableStateOf("") }
        var passwordText by remember { mutableStateOf("") }
        Column(modifier = Modifier.fillMaxSize()) {
            val context = LocalContext.current
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth()
                ) {
                    FormInputField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = Layout1, end = Layout1, bottom = Layout1),
                        type = InputFieldType.Text,
                        text = emailText,
                        placeHolder = "Email",
                        onValueChange = {
                            emailText = it
                        }
                    )
                    FormInputField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = Layout1, end = Layout1, bottom = Layout1),
                        type = InputFieldType.Password,
                        text = passwordText,
                        placeHolder = ".........",
                        onValueChange = {
                            passwordText = it
                        }
                    )
                }
            }
            ButtonsBar(
                mainButton = {
                    ButtonFill(
                        text = "Login",
                        isLoading = emailLoginButtonState,
                        onClick = {
                            viewModel.loading()
                            logUser(
                                context = context,
                                viewModel = viewModel,
                                email = emailText,
                                password = passwordText,
                                auth = auth
                            )
                        }
                    )
                    Eyebrow(
                        text = "Doesn't have an account ? Create one !",
                        modifier = Modifier
                            .padding(start = Layout1, end = Layout1, bottom = Basic2)
                            .clickable { onAccountCreationClick() }
                    )
                },
                secondaryButton = {
                    GoogleAuth(
                        viewModel = hiltViewModel(),
                        onLoginSuccess = { user ->
                            Toast.makeText(context, user.displayName, Toast.LENGTH_LONG).show()
                            onLoginWithGoogle(user)
                        }
                    )
                }
            )
        }
    })
    user?.let {
        onLoginWithEmail(it)
    }
}

private fun logUser(
    context: Context,
    viewModel: LoginScreenViewModel,
    email: String,
    password: String,
    auth: FirebaseAuth,
) {
    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d("LoginActivity", "signInWithEmail:success")
                task.result.user?.let {
                    viewModel.signIn(it)
                }
            } else {
                // If sign in fails, display a message to the user.
                Log.w("LoginActivity", "signInWithEmail:failure", task.exception)
                Toast.makeText(context, "Authentication failed.", Toast.LENGTH_SHORT).show()
                viewModel.signInFail()
            }
        }
}