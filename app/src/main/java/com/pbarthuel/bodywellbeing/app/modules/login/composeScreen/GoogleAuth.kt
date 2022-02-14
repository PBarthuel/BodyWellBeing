package com.pbarthuel.bodywellbeing.app.modules.login.composeScreen

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.pbarthuel.bodywellbeing.R
import com.pbarthuel.bodywellbeing.app.modules.login.utils.googleAuth.GoogleAuthResultContract
import com.pbarthuel.bodywellbeing.app.ui.component.button.ButtonOutlined
import com.pbarthuel.bodywellbeing.app.ui.component.button.IconSpec
import com.pbarthuel.bodywellbeing.viewModel.modules.login.LoginViewModel

@ExperimentalMaterialApi
@Composable
fun GoogleAuth(
    viewModel: LoginViewModel,
    loginButtonState: Boolean
) {
    val signInRequestCode = 1

    val authResultLauncher = rememberLauncherForActivityResult(contract = GoogleAuthResultContract()) { task ->
        viewModel.loginWithGoogle(task)
    }

    ButtonOutlined(
        text = stringResource(id = R.string.signin_google),
        isLoading = loginButtonState,
        startIconSpec = IconSpec.IconPainter(painter = painterResource(id = R.drawable.ic_google_logo))
    ) { authResultLauncher.launch(signInRequestCode) }
}