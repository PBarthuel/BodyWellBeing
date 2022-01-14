package com.pbarthuel.bodywellbeing.app.modules.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.lifecycle.Observer
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.pbarthuel.bodywellbeing.app.modules.home.HomeActivity
import com.pbarthuel.bodywellbeing.app.modules.main.compose.CreateAccountScreen
import com.pbarthuel.bodywellbeing.app.modules.main.compose.LoginScreen
import com.pbarthuel.bodywellbeing.app.ui.theme.BodyWellBeingTheme
import com.pbarthuel.bodywellbeing.viewModel.modules.main.MainViewModel
import com.pbarthuel.bodywellbeing.viewModel.modules.main.State
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth
        viewModel.isAlreadyLog(auth)

        setContent {
            var loginButtonState by remember { mutableStateOf(false) }
            BodyWellBeingTheme {
                ProvideWindowInsets {
                    val navController = rememberAnimatedNavController()
                    Column(modifier = Modifier.fillMaxSize()) {
                        Column(modifier = Modifier.weight(1f)) {
                            AnimatedNavHost(
                                navController,
                                startDestination = MainDestinations.Login.root,
                                enterTransition = { fadeIn(animationSpec = tween(700)) }, // TODO check if it works when removing this parameter with accompanist accompanist > 0.19.0
                                exitTransition = { fadeOut(animationSpec = tween(700)) }
                            ) {
                                composable(route = MainDestinations.Login.root) {
                                    LoginScreen(
                                        auth = auth,
                                        loginButtonState = loginButtonState,
                                        viewModel = viewModel,
                                        onAccountCreationClick = {
                                            navController.navigate(route = MainDestinations.CreateAccount.root)
                                        }
                                    )
                                }
                                composable(route = MainDestinations.CreateAccount.root) {
                                    CreateAccountScreen(
                                        auth = auth,
                                        loginButtonState = loginButtonState,
                                        viewModel = viewModel,
                                        onLoginClick = {
                                            navController.navigate(route = MainDestinations.Login.root)
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }

            viewModel.state.observe(this, Observer {
                when (val s = it) {
                    is State.Error -> {
                        Toast.makeText(this, s.errorMessage, Toast.LENGTH_LONG).show()
                        loginButtonState = false
                    }
                    State.Loading -> {
                        loginButtonState = true
                    }
                    is State.Success -> {
                        loginButtonState = false
                        viewModel.loginSuccess()
                        startActivity(Intent(this, HomeActivity::class.java))
                    }
                    null -> { }
                }
            })
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.isAlreadyLog(auth = auth)
    }
}

object MainDestinations {
    const val Main = "main"

    object Login {
        const val root = "login"
    }

    object CreateAccount {
        const val root = "create-account"
    }
}