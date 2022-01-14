package com.pbarthuel.bodywellbeing.app.modules.main.compose

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.android.gms.common.api.ApiException
import com.pbarthuel.bodywellbeing.R
import com.pbarthuel.bodywellbeing.app.models.User
import com.pbarthuel.bodywellbeing.app.modules.main.utils.googleAuth.GoogleAuthResultContract
import com.pbarthuel.bodywellbeing.app.ui.component.ButtonOutlined
import com.pbarthuel.bodywellbeing.app.ui.component.IconSpec
import com.pbarthuel.bodywellbeing.viewModel.modules.main.compose.GoogleAuthViewModel

@ExperimentalMaterialApi
@Composable
fun GoogleAuth(
    viewModel: GoogleAuthViewModel,
    onLoginSuccess: (User) -> Unit
) {
    var text by remember { mutableStateOf<String?>(null) }
    val user by remember(viewModel) { viewModel.user }.collectAsState()
    val googleLoginButtonState by remember(viewModel) { viewModel.googleLoginButtonState }.collectAsState()
    val signInRequestCode = 1

    val authResultLauncher =
        rememberLauncherForActivityResult(contract = GoogleAuthResultContract()) { task ->
            try {
                val account = task?.getResult(ApiException::class.java)
                if (account == null) {
                    text = "Google sign in failed"
                    viewModel.signInFail()
                } else {
                    viewModel.signIn(
                        email = account.email ?: "",
                        displayName = account.displayName ?: "",
                    )
                }
            } catch (e: ApiException) {
                text = "Google sign in failed"
                viewModel.signInFail()
            }
        }

    GoogleAuthView(
        viewModel = viewModel,
        googleLoginButtonState = googleLoginButtonState,
        errorText = text,
        onClick = {
            text = null
            authResultLauncher.launch(signInRequestCode)
        }
    )

    user?.let {
        onLoginSuccess(it)
    }
}

@ExperimentalMaterialApi
@Composable
fun GoogleAuthView(
    viewModel: GoogleAuthViewModel,
    googleLoginButtonState: Boolean,
    errorText: String?,
    onClick: () -> Unit
) {
    ButtonOutlined(
        text = "Sign in with Google",
        isLoading = googleLoginButtonState,
        startIconSpec = IconSpec.IconPainter(painter = painterResource(id = R.drawable.ic_google_logo))
    ) {
        viewModel.loading()
        onClick()
    }

    errorText?.let {
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = it)
    }
}