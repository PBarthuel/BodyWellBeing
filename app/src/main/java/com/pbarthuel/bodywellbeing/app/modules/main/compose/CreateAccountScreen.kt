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
import com.google.firebase.auth.FirebaseAuth
import com.pbarthuel.bodywellbeing.app.models.User
import com.pbarthuel.bodywellbeing.app.ui.component.ButtonFill
import com.pbarthuel.bodywellbeing.app.ui.component.input.FormInputField
import com.pbarthuel.bodywellbeing.app.ui.component.input.InputFieldType
import com.pbarthuel.bodywellbeing.app.ui.component.text.Eyebrow
import com.pbarthuel.bodywellbeing.app.ui.template.ButtonsBar
import com.pbarthuel.bodywellbeing.app.ui.theme.Basic2
import com.pbarthuel.bodywellbeing.app.ui.theme.Layout1
import com.pbarthuel.bodywellbeing.viewModel.modules.main.compose.CreateAccountScreenViewModel

@Composable
fun CreateAccountScreen(
    auth: FirebaseAuth,
    viewModel: CreateAccountScreenViewModel,
    onLoginWithEmailClick: () -> Unit,
    onAccountCreated: (User) -> Unit
) {
    val user by remember(viewModel) { viewModel.user }.collectAsState()
    val accountCreationButtonState by remember(viewModel) { viewModel.accountCreationButtonState }.collectAsState()
    Scaffold(content = {
        var emailText by remember { mutableStateOf("") }
        var passwordText by remember { mutableStateOf("") }
        var verifyPasswordText by remember { mutableStateOf("") }
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
                    FormInputField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = Layout1, end = Layout1, bottom = Layout1),
                        type = InputFieldType.Password,
                        text = verifyPasswordText,
                        placeHolder = ".........",
                        onValueChange = {
                            verifyPasswordText = it
                        }
                    )
                }
            }
            ButtonsBar(
                mainButton = {
                    ButtonFill(
                        text = "Create account",
                        isLoading = accountCreationButtonState,
                        onClick = {
                            viewModel.loading()
                            createUser(
                                context = context,
                                viewModel = viewModel,
                                email = emailText,
                                password = passwordText,
                                auth = auth
                            )
                        }
                    )
                    Eyebrow(
                        text = "Already have an account ?",
                        modifier = Modifier
                            .padding(start = Layout1, end = Layout1, bottom = Basic2)
                            .clickable { onLoginWithEmailClick() }
                    )
                }
            )
        }
    })
    user?.let {
        onAccountCreated(it)
    }
}

private fun createUser(
    auth: FirebaseAuth,
    viewModel: CreateAccountScreenViewModel,
    context: Context,
    email: String,
    password: String,
) {
    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            when (task.isSuccessful) {
                true -> {
                    Log.d("LoginActivity", "createUserWithEmail:success")
                    task.result.user?.let {
                        viewModel.createAccount(it)
                    }
                }
                else -> {
                    Log.w("LoginActivity", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(context, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    viewModel.signInFail()
                }
            }
        }
}