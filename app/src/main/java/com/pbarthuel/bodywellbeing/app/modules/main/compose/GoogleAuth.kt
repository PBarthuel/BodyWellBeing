package com.pbarthuel.bodywellbeing.app.modules.main.compose

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.pbarthuel.bodywellbeing.R
import com.pbarthuel.bodywellbeing.app.modules.main.utils.googleAuth.GoogleAuthResultContract
import com.pbarthuel.bodywellbeing.app.ui.component.ButtonOutlined
import com.pbarthuel.bodywellbeing.app.ui.component.IconSpec
import com.pbarthuel.bodywellbeing.viewModel.modules.main.MainViewModel

@ExperimentalMaterialApi
@Composable
fun GoogleAuth(
    viewModel: MainViewModel,
    loginButtonState: Boolean
) {
    val signInRequestCode = 1

    val authResultLauncher =
        rememberLauncherForActivityResult(contract = GoogleAuthResultContract()) { task ->
            viewModel.loginWithGoogle(task)
        }

    ButtonOutlined(
        text = "Sign in with Google",
        isLoading = loginButtonState,
        startIconSpec = IconSpec.IconPainter(painter = painterResource(id = R.drawable.ic_google_logo))
    ) { authResultLauncher.launch(signInRequestCode) }
}