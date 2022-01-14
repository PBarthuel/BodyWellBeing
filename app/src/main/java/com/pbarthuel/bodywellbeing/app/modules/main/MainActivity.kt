package com.pbarthuel.bodywellbeing.app.modules.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material.ExperimentalMaterialApi
import androidx.hilt.navigation.compose.hiltViewModel
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
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth

        setContent {
            BodyWellBeingTheme {
                ProvideWindowInsets {
                    val navController = rememberAnimatedNavController()
                    AnimatedNavHost(
                        navController,
                        startDestination = MainDestinations.Login.root,
                        enterTransition = { fadeIn(animationSpec = tween(700)) }, // TODO check if it works when removing this parameter with accompanist accompanist > 0.19.0
                        exitTransition = { fadeOut(animationSpec = tween(700)) }
                    ) {
                        composable(route = MainDestinations.Login.root) {
                            LoginScreen(
                                auth = auth,
                                hiltViewModel(),
                                onAccountCreationClick = {
                                    navController.navigate(route = MainDestinations.CreateAccount.root)
                                },
                                onLoginWithGoogle = {

                                },
                                onLoginWithEmail = {

                                }
                            )
                        }
                        composable(route = MainDestinations.CreateAccount.root) {
                            CreateAccountScreen(
                                auth = auth,
                                viewModel = hiltViewModel(),
                                onLoginWithEmailClick = {
                                    navController.navigate(route = MainDestinations.Login.root)
                                },
                                onAccountCreated = {

                                }
                            )
                        }
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        auth.currentUser?.let {
            startActivity(Intent(this, HomeActivity::class.java))
        }
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