package com.pbarthuel.bodywellbeing.app.modules.accountCreation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import com.google.accompanist.insets.ProvideWindowInsets
import com.pbarthuel.bodywellbeing.app.ui.theme.BodyWellBeingTheme
import com.pbarthuel.bodywellbeing.viewModel.modules.accountCreation.AccountCreationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountCreationActivity : ComponentActivity() {

    private val viewModel by viewModels<AccountCreationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BodyWellBeingTheme {
                ProvideWindowInsets {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(text = "Bottom app bar example")
                                },
                                navigationIcon = {
                                    IconButton(
                                        onClick = {
                                            kotlin.runCatching {
                                                viewModel.logOut()
                                            }.onSuccess {
                                                finish()
                                            }
                                        }
                                    ) { Icon(Icons.Filled.Menu, contentDescription = "") }
                                },
                                elevation = AppBarDefaults.TopAppBarElevation
                            )
                        }
                    ) {
                        Column(modifier = Modifier.fillMaxSize()) {
                            Text(
                                modifier = Modifier.align(CenterHorizontally),
                                text = viewModel.id ?: "error"
                            )
                        }
                    }
                }
            }
        }
    }
}