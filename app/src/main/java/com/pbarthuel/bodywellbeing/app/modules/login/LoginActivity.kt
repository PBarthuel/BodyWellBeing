package com.pbarthuel.bodywellbeing.app.modules.login

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.core.animation.doOnEnd
import androidx.lifecycle.lifecycleScope
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.pbarthuel.bodywellbeing.R
import com.pbarthuel.bodywellbeing.app.modules.accountCreation.AccountCreationActivity
import com.pbarthuel.bodywellbeing.app.modules.login.composeScreen.CreateAccountScreen
import com.pbarthuel.bodywellbeing.app.modules.login.composeScreen.LoginScreen
import com.pbarthuel.bodywellbeing.app.modules.main.MainActivity
import com.pbarthuel.bodywellbeing.app.ui.theme.BodyWellBeingTheme
import com.pbarthuel.bodywellbeing.viewModel.modules.login.LoginState
import com.pbarthuel.bodywellbeing.viewModel.modules.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class LoginActivity : ComponentActivity() {

    private val viewModel by viewModels<LoginViewModel>()

    private lateinit var auth: FirebaseAuth

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.isAlreadyLog()
        auth = Firebase.auth

        splashScreen.setOnExitAnimationListener { splashScreenView ->
            val slideUp = ObjectAnimator.ofFloat(
                splashScreenView,
                View.TRANSLATION_Y,
                0f,
                -splashScreenView.height.toFloat()
            )
            slideUp.interpolator = AnticipateInterpolator()
            slideUp.duration = 200L
            slideUp.doOnEnd { splashScreenView.remove() }
            slideUp.start()
        }

        setContent {
            val navController = rememberAnimatedNavController()
            var loginButtonState by remember { mutableStateOf(false) }
            BodyWellBeingTheme {
                ProvideWindowInsets {
                    Column(modifier = Modifier.fillMaxSize()) {
                        AnimatedNavHost(
                            navController,
                            startDestination = LoginDestinations.splashScreen,
                            enterTransition = { fadeIn(animationSpec = tween(700)) },
                            exitTransition = { fadeOut(animationSpec = tween(700)) }
                        ) {
                            composable(route = LoginDestinations.splashScreen) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(color = MaterialTheme.colors.background)
                                ) {
                                    val scale = remember { Animatable(0f) }
                                    LaunchedEffect(key1 = null, block = {
                                        scale.animateTo(
                                            targetValue = 0.5f,
                                            animationSpec = tween(
                                                durationMillis = 500,
                                                easing = {
                                                    OvershootInterpolator(2f).getInterpolation(
                                                        it
                                                    )
                                                }
                                            )
                                        )
                                    })
                                    Image(
                                        modifier = Modifier
                                            .align(Alignment.Center)
                                            .scale(scale.value),
                                        painter = painterResource(id = R.drawable.ic_heart_red),
                                        contentDescription = null
                                    )
                                }
                            }
                            composable(route = LoginDestinations.login) {
                                LoginScreen(
                                    auth = auth,
                                    loginButtonState = loginButtonState,
                                    viewModel = viewModel,
                                    onAccountCreationClick = {
                                        navController.navigate(route = LoginDestinations.createAccount)
                                    }
                                )
                            }
                            composable(route = LoginDestinations.createAccount) {
                                CreateAccountScreen(
                                    auth = auth,
                                    loginButtonState = loginButtonState,
                                    viewModel = viewModel,
                                    onLoginClick = {
                                        navController.navigate(route = LoginDestinations.login)
                                    }
                                )
                            }
                        }
                    }
                }
            }

            LaunchedEffect(key1 = "", block = {
                lifecycleScope.launch {
                    viewModel.state.collect {
                        when (it) {
                            is LoginState.Error -> {
                                loginButtonState = false
                            }
                            LoginState.ButtonLoading -> {
                                loginButtonState = true
                            }
                            is LoginState.Logged -> {
                                loginButtonState = false
                                viewModel.loginSuccess(auth)
                                delay(1000)
                                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                            }
                            is LoginState.CreateAccount -> {
                                loginButtonState = false
                                viewModel.loginSuccess(auth)
                                delay(1000)
                                startActivity(
                                    Intent(
                                        this@LoginActivity,
                                        AccountCreationActivity::class.java
                                    )
                                )
                            }
                            is LoginState.Login -> {
                                delay(1000)
                                navController.navigate(route = LoginDestinations.login)
                            }
                            else -> {}
                        }
                    }
                }
            })
        }
    }
}

object LoginDestinations {
    const val splashScreen = "splash-screen"
    const val login = "login"
    const val createAccount = "create-account"
}