package com.pbarthuel.bodywellbeing.app.modules.settings

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.accompanist.insets.ProvideWindowInsets
import com.pbarthuel.bodywellbeing.app.modules.login.LoginActivity
import com.pbarthuel.bodywellbeing.app.ui.component.button.ButtonFill
import com.pbarthuel.bodywellbeing.app.ui.theme.BodyWellBeingTheme
import com.pbarthuel.bodywellbeing.app.ui.theme.HorizontalMargin
import com.pbarthuel.bodywellbeing.viewModel.modules.settings.SettingsScreenState
import com.pbarthuel.bodywellbeing.viewModel.modules.settings.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsActivity : ComponentActivity() {

    private val viewModel by viewModels<SettingsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val screenState = viewModel.screenState.collectAsState()
            BodyWellBeingTheme {
                ProvideWindowInsets {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        content = {
                            Box(modifier = Modifier.fillMaxSize()) {
                                if (screenState.value == SettingsScreenState.Loading) {
                                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                                }
                                LazyColumn(modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = HorizontalMargin)
                                ) {

                                }
                            }
                        },
                        bottomBar = {
                            ButtonFill(
                                text = "Logout",
                                modifier = Modifier.padding(all = HorizontalMargin)
                            ) { viewModel.logOut() }
                        }
                    )
                }
                when (screenState.value) {
                    SettingsScreenState.Logout -> {
                        Intent(this, LoginActivity::class.java).also {
                            it.flags =
                                Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(it)
                        }
                    }
                    else -> {}
                }
            }
        }
    }
}