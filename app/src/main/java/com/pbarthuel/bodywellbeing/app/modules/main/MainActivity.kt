package com.pbarthuel.bodywellbeing.app.modules.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.pbarthuel.bodywellbeing.R
import com.pbarthuel.bodywellbeing.app.modules.profile.ProfileScreen
import com.pbarthuel.bodywellbeing.app.ui.theme.BodyWellBeingTheme
import com.pbarthuel.bodywellbeing.viewModel.modules.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BodyWellBeingTheme {
                ProvideWindowInsets {
                    val navController = rememberAnimatedNavController()
                    val bottomNavigationItems = listOf(
                        MainBottomBarNavigation.Home,
                        MainBottomBarNavigation.Body,
                        MainBottomBarNavigation.Profile
                    )
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        topBar = {
                            TopAppBar(
                                backgroundColor = Color.Transparent.copy(alpha = 0f),
                                elevation = 0.dp,
                                title = { },
                                actions = {
                                    IconButton(
                                        onClick = { }
                                    ) { Icon(Icons.Filled.Settings, contentDescription = "Settings") }
                                }
                            )
                        }
                    ) {
                        Column(modifier = Modifier.fillMaxSize()) {
                            Box(modifier = Modifier
                                .fillMaxSize()
                                .weight(1f)) {
                                AnimatedNavHost(
                                    navController,
                                    startDestination = MainBottomBarNavigation.Home.route,
                                    enterTransition = { fadeIn(animationSpec = tween(700)) },
                                    exitTransition = { fadeOut(animationSpec = tween(700)) }
                                ) {
                                    composable(MainBottomBarNavigation.Home.route) {
                                        Box(modifier = Modifier.fillMaxSize()) {
                                        }
                                    }
                                    composable(MainBottomBarNavigation.Body.route) {
                                        Box(modifier = Modifier.fillMaxSize()) {
                                        }
                                    }
                                    composable(MainBottomBarNavigation.Profile.route) {
                                        ProfileScreen(
                                            profileScreenViewModel = hiltViewModel(),
                                            context = this@MainActivity
                                        )
                                    }
                                }
                            }
                            BottomNavigation {
                                val navBackStackEntry by navController.currentBackStackEntryAsState()
                                val currentDestination = navBackStackEntry?.destination
                                bottomNavigationItems.forEach { screen ->
                                    BottomNavigationItem(
                                        icon = { Icon(screen.icon, contentDescription = null) },
                                        label = { Text(stringResource(id = screen.resourceId)) },
                                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                        onClick = {
                                            navController.navigate(screen.route) {
                                                popUpTo(navController.graph.findStartDestination().id) {
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

sealed class MainBottomBarNavigation(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    object Home : MainBottomBarNavigation("home", R.string.home, Icons.Filled.Home)
    object Body : MainBottomBarNavigation("body", R.string.body, Icons.Filled.AddCircle)
    object Profile : MainBottomBarNavigation("profile", R.string.profile, Icons.Filled.Person)
}