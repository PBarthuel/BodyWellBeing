package com.pbarthuel.bodywellbeing.app.modules.main.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.firebase.auth.FirebaseAuth
import com.pbarthuel.bodywellbeing.app.ui.component.ButtonFill
import com.pbarthuel.bodywellbeing.app.ui.component.input.FormInputField
import com.pbarthuel.bodywellbeing.app.ui.component.input.InputFieldType
import com.pbarthuel.bodywellbeing.app.ui.component.text.Eyebrow
import com.pbarthuel.bodywellbeing.app.ui.template.ButtonsBar
import com.pbarthuel.bodywellbeing.app.ui.theme.Basic2
import com.pbarthuel.bodywellbeing.app.ui.theme.HorizontalMargin
import com.pbarthuel.bodywellbeing.app.ui.theme.Layout1
import com.pbarthuel.bodywellbeing.app.ui.theme.Layout2
import com.pbarthuel.bodywellbeing.viewModel.modules.main.MainViewModel

@ExperimentalMaterialApi
@Composable
fun LoginScreen(
    loginButtonState: Boolean,
    auth: FirebaseAuth,
    viewModel: MainViewModel,
    onAccountCreationClick: () -> Unit
) {
    Scaffold(content = {
        var emailText by remember { mutableStateOf("") }
        var passwordText by remember { mutableStateOf("") }
        Column(modifier = Modifier.fillMaxSize()) {
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
                            .padding(start = HorizontalMargin, end = HorizontalMargin, bottom = Layout1),
                        label = "Email",
                        type = InputFieldType.Text,
                        text = emailText,
                        placeHolder = "user@gmail.com",
                        onValueChange = {
                            emailText = it
                        }
                    )
                    val inputTypePassword = remember { mutableStateOf(InputFieldType.Password) }
                    PasswordInputField(
                        label = "Confirm password",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = HorizontalMargin, end = HorizontalMargin, top = Layout2),
                        inputType = inputTypePassword,
                        text = passwordText,
                        onValueChange = {
                            passwordText = it
                        },
                        focusRequester = FocusRequester(),
                        imeAction = ImeAction.Done,
                        onImeAction = { },
                    )
                }
            }
            ButtonsBar(
                mainButton = {
                    ButtonFill(
                        text = "Login",
                        isLoading = loginButtonState,
                        onClick = {
                            viewModel.logUser(
                                email = emailText,
                                password = passwordText,
                                auth = auth
                            )
                        }
                    )
                    Eyebrow(
                        text = "Doesn't have an account ? Create one !",
                        modifier = Modifier
                            .padding(start = HorizontalMargin, end = HorizontalMargin, bottom = Basic2)
                            .clickable { onAccountCreationClick() }
                    )
                },
                secondaryButton = {
                    GoogleAuth(
                        viewModel = hiltViewModel(),
                        loginButtonState = loginButtonState,
                    )
                }
            )
        }
    })
}