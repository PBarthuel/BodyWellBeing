package com.pbarthuel.bodywellbeing.app.modules.accountCreation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.pbarthuel.bodywellbeing.app.modules.accountCreation.composeScreen.UserInfoScreen
import com.pbarthuel.bodywellbeing.app.ui.theme.BodyWellBeingTheme
import com.pbarthuel.bodywellbeing.viewModel.modules.accountCreation.AccountCreationViewModel
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalAnimationApi
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
                        viewModel.logOut()
                    }
                    AnimatedNavHost(
                        navController,
                        startDestination = AccountCreationDestinations.AppInfo.root,
                        enterTransition = { fadeIn(animationSpec = tween(700)) },
                        exitTransition = { fadeOut(animationSpec = tween(700)) }
                    ) {
                        composable(AccountCreationDestinations.AppInfo.root) {

                        }
                        composable(AccountCreationDestinations.UserInfo.root) {
                            UserInfoScreen(viewModel = viewModel)
                        }
                    }
                }
            }
        }
    }
}

object AccountCreationDestinations {

    object AppInfo {
        const val root = "app-info"
    }

    object UserInfo {
        const val root = "user-info"
    }
}