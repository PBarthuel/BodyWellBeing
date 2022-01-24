package com.pbarthuel.bodywellbeing.app.modules.main.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import com.google.firebase.auth.FirebaseAuth
import com.pbarthuel.bodywellbeing.R
import com.pbarthuel.bodywellbeing.app.ui.component.ButtonFill
import com.pbarthuel.bodywellbeing.app.ui.component.input.FormInputField
import com.pbarthuel.bodywellbeing.app.ui.component.input.InputFieldType
import com.pbarthuel.bodywellbeing.app.ui.component.text.Eyebrow
import com.pbarthuel.bodywellbeing.app.ui.template.ButtonsBar
import com.pbarthuel.bodywellbeing.app.ui.theme.HorizontalMargin
import com.pbarthuel.bodywellbeing.app.ui.theme.Layout1
import com.pbarthuel.bodywellbeing.app.ui.theme.Layout2
import com.pbarthuel.bodywellbeing.viewModel.modules.main.MainViewModel

@ExperimentalComposeUiApi
@Composable
fun CreateAccountScreen(
    loginButtonState: Boolean,
    auth: FirebaseAuth,
    viewModel: MainViewModel,
    onLoginClick: () -> Unit
) {
    var emailText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }
    var confirmedPasswordText by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .align(Center)
                    .fillMaxWidth()
            ) {
                FormInputField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = HorizontalMargin,
                            end = HorizontalMargin,
                            bottom = Layout2
                        ),
                    label = "Email",
                    type = InputFieldType.Text,
                    text = emailText,
                    placeHolder = "user@gmail.com",
                    onValueChange = {
                        emailText = it
                    }
                )
                PasswordConfigurationLayout(
                    title = "Password",
                    focusRequester = FocusRequester(),
                    passwordText = passwordText,
                    onPasswordsChange = { password, confirmedPassword ->
                        passwordText = password
                        confirmedPasswordText = confirmedPassword
                    },
                    onDone = {
                        keyboardController?.hide()
                        viewModel.createAccount(
                            auth = auth,
                            email = emailText,
                            password = passwordText,
                            confirmedPassword = confirmedPasswordText
                        )
                    }
                )
            }
        }
        ButtonsBar(
            mainButton = {
                ButtonFill(
                    text = "Create account",
                    isLoading = loginButtonState,
                    onClick = {
                        keyboardController?.hide()
                        viewModel.createAccount(
                            auth = auth,
                            email = emailText,
                            password = passwordText,
                            confirmedPassword = confirmedPasswordText
                        )
                    }
                )
                Eyebrow(
                    text = "Already have an account ?",
                    modifier = Modifier
                        .padding(horizontal = Layout1)
                        .clickable { onLoginClick() }
                )
            }
        )
    }
}

@Composable
fun PasswordConfigurationLayout(
    passwordText: String = "",
    confirmedPassword: String = "",
    title: String,
    focusRequester: FocusRequester,
    onPasswordsChange: (String, String) -> Unit,
    onDone: () -> Unit
) {
    val helperText = remember { mutableStateOf<Int?>(null) }

    var passwordStatus by remember { mutableStateOf(0) } // TODO changer le status avec du regex

    var password by remember { mutableStateOf(passwordText) }
    var confirmPassword by remember { mutableStateOf(confirmedPassword) }

    val inputTypePassword = remember { mutableStateOf(InputFieldType.Password) }
    val inputTypeConfirm = remember { mutableStateOf(InputFieldType.Password) }

    val repeatPasswordFocusRequester = remember { FocusRequester() }
    PasswordInputField(
        label = title,
        modifier = Modifier.padding(horizontal = HorizontalMargin),
        inputType = inputTypePassword,
        text = password,
        helperText = helperText.value,
        onValueChange = {
            password = it
            onPasswordsChange(password, confirmPassword)
        },
        gaugeLevel = passwordStatus,
        focusRequester = focusRequester,
        imeAction = ImeAction.Next,
        onImeAction = { repeatPasswordFocusRequester.requestFocus() },
    )
    PasswordInputField(
        label = "Confirm password",
        modifier = Modifier.padding(start = HorizontalMargin, end = HorizontalMargin, top = Layout2),
        inputType = inputTypeConfirm,
        text = confirmPassword,
        onValueChange = {
            confirmPassword = it
            onPasswordsChange(password, confirmPassword)
        },
        focusRequester = repeatPasswordFocusRequester,
        imeAction = ImeAction.Done,
        onImeAction = onDone,
    )
}

@Composable
fun PasswordInputField(
    modifier: Modifier = Modifier,
    label: String,
    inputType: MutableState<InputFieldType>,
    text: String,
    helperText: Int? = null,
    gaugeLevel: Int? = null,
    onValueChange: (String) -> Unit,
    focusRequester: FocusRequester,
    imeAction: ImeAction,
    onImeAction: () -> Unit
) {
    FormInputField(
        modifier = modifier,
        type = inputType.value,
        label = label,
        text = text,
        placeHolder = "••••••",
        helperText = helperText?.let { AnnotatedString(stringResource(id = it)) },
        rightAccessory = { SecretIconPassword(inputType = inputType) },
        onValueChange = onValueChange,
        gaugeLevel = gaugeLevel,
        focusRequester = focusRequester,
        imeAction = imeAction,
        onImeAction = onImeAction,
    )
}

@Composable
fun SecretIconPassword(
    inputType: MutableState<InputFieldType>,
    tint: Color = MaterialTheme.colors.onSurface
) {
    var passwordIcon by remember { mutableStateOf(R.drawable.ic_stock_eye) }
    Icon(
        modifier = Modifier.clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ) {
            when (inputType.value) {
                InputFieldType.Password -> {
                    passwordIcon = R.drawable.ic_stock_eye2
                    inputType.value = InputFieldType.PasswordClear
                }
                InputFieldType.PasswordClear -> {
                    passwordIcon = R.drawable.ic_stock_eye
                    inputType.value = InputFieldType.Password
                }
                else -> {}
            }
        },
        painter = painterResource(id = passwordIcon),
        contentDescription = "Toggle password visibility",
        tint = tint
    )
}