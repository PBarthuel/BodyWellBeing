package com.pbarthuel.bodywellbeing.app.modules.accountCreation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.pbarthuel.bodywellbeing.app.modules.accountCreation.composeScreen.AppInfoScreen
import com.pbarthuel.bodywellbeing.app.modules.accountCreation.composeScreen.UserInfoScreen
import com.pbarthuel.bodywellbeing.app.modules.login.LoginActivity
import com.pbarthuel.bodywellbeing.app.ui.theme.BodyWellBeingTheme
import com.pbarthuel.bodywellbeing.viewModel.modules.accountCreation.AccountCreationViewModel
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class AccountCreationActivity : ComponentActivity() {

    private val viewModel by viewModels<AccountCreationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BodyWellBeingTheme {
                ProvideWindowInsets {
                    val navController = rememberAnimatedNavController()
                    BackHandler {
                        kotlin.runCatching {
                            viewModel.logOut()
                        }.onSuccess {
                            Intent(this, LoginActivity::class.java).also {
                                it.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                startActivity(it)
                            }
                        }
                    }
                    AnimatedNavHost(
                        navController,
                        startDestination = AccountCreationDestinations.appInfo,
                        enterTransition = { fadeIn(animationSpec = tween(700)) },
                        exitTransition = { fadeOut(animationSpec = tween(700)) }
                    ) {
                        composable(AccountCreationDestinations.appInfo) {
                            AppInfoScreen(viewModel = viewModel) {
                                navController.navigate(AccountCreationDestinations.userInfo)
                            }
                        }
                        composable(AccountCreationDestinations.userInfo) {
                            UserInfoScreen(
                                viewModel = viewModel
                            )
                        }
                        composable(AccountCreationDestinations.congrats) {

                        }
                    }
                }
            }
        }
    }
}

object AccountCreationDestinations {
    const val appInfo = "app-info"
    const val userInfo = "user-info"
    const val congrats = "congrats"
}