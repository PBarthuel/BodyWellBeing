package com.pbarthuel.bodywellbeing.app.modules.login.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.firebase.auth.FirebaseAuth
import com.pbarthuel.bodywellbeing.R
import com.pbarthuel.bodywellbeing.app.ui.component.ButtonFill
import com.pbarthuel.bodywellbeing.app.ui.component.input.FormInputField
import com.pbarthuel.bodywellbeing.app.ui.component.input.InputFieldType
import com.pbarthuel.bodywellbeing.app.ui.component.text.Eyebrow
import com.pbarthuel.bodywellbeing.app.ui.template.ButtonsBar
import com.pbarthuel.bodywellbeing.app.ui.theme.Basic2
import com.pbarthuel.bodywellbeing.app.ui.theme.HorizontalMargin
import com.pbarthuel.bodywellbeing.app.ui.theme.Layout1
import com.pbarthuel.bodywellbeing.viewModel.modules.login.LoginViewModel

@ExperimentalMaterialApi
@Composable
fun LoginScreen(
    loginButtonState: Boolean,
    auth: FirebaseAuth,
    viewModel: LoginViewModel,
    onAccountCreationClick: () -> Unit
) {
    var emailText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colors.background)) {
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
                    label = stringResource(id = R.string.mail),
                    type = InputFieldType.Text,
                    text = emailText,
                    placeHolder = stringResource(id = R.string.mail_placeholder),
                    onValueChange = {
                        emailText = it
                    }
                )
                val inputTypePassword = remember { mutableStateOf(InputFieldType.Password) }
                PasswordInputField(
                    label = stringResource(id = R.string.password),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = HorizontalMargin),
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
                    text = stringResource(id = R.string.login),
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
                    text = stringResource(id = R.string.dosent_have_account),
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
}