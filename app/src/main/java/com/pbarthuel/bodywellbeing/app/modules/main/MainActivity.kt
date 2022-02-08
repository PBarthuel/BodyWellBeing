package com.pbarthuel.bodywellbeing.app.modules.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
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
import com.pbarthuel.bodywellbeing.app.modules.login.LoginActivity
import com.pbarthuel.bodywellbeing.app.modules.profile.ProfileScreen
import com.pbarthuel.bodywellbeing.app.ui.component.text.Header2
import com.pbarthuel.bodywellbeing.app.ui.theme.Basic1
import com.pbarthuel.bodywellbeing.app.ui.theme.BodyWellBeingTheme
import com.pbarthuel.bodywellbeing.viewModel.modules.main.MainScreenState
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

        viewModel.createExercise()

        setContent {
            BodyWellBeingTheme {
                ProvideWindowInsets {
                    BackHandler {

                    }
                    val navController = rememberAnimatedNavController()
                    val bottomNavigationItems = listOf(
                        MainBottomBarNavigation.Home,
                        MainBottomBarNavigation.Body,
                        MainBottomBarNavigation.Exercises,
                        MainBottomBarNavigation.Profile
                    )
                    val screenState = viewModel.screenState.collectAsState()
                    var topBarTitle by remember { mutableStateOf(getString(R.string.home)) }
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        topBar = {
                            TopAppBar(
                                modifier = Modifier.padding(Basic1),
                                backgroundColor = Color.Transparent.copy(alpha = 0f),
                                elevation = 2.dp,
                                title = { Header2(text = topBarTitle) },
                                actions = {
                                    // TODO trouver une meilleur solution pour les actions
                                    when (screenState.value) {
                                        MainScreenState.Profile -> {
                                            IconButton(
                                                onClick = { onSettingsClicked() }
                                            ) { Icon(Icons.Filled.Settings, contentDescription = "Settings") }
                                        }
                                        MainScreenState.Logout -> {
                                            Intent(this@MainActivity, LoginActivity::class.java).also {
                                                it.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                                startActivity(it)
                                            }
                                        }
                                        else -> { }
                                    }
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
                                    startDestination = MainBottomBarNavigation.Home.root,
                                    enterTransition = { fadeIn(animationSpec = tween(700)) },
                                    exitTransition = { fadeOut(animationSpec = tween(700)) }
                                ) {
                                    composable(MainBottomBarNavigation.Home.root) {
                                        viewModel.onScreenChanged(MainScreenState.Home)
                                        Box(modifier = Modifier.fillMaxSize().background(colorResource(id = R.color.teal_700))) {
                                        }
                                    }
                                    composable(MainBottomBarNavigation.Body.root) {
                                        viewModel.onScreenChanged(MainScreenState.Body)
                                        Box(modifier = Modifier.fillMaxSize().background(BodyWellBeingTheme.colors.actionSecondary)) {
                                        }
                                    }
                                    composable(MainBottomBarNavigation.Exercises.root) {
                                        viewModel.onScreenChanged(MainScreenState.Exercises)
                                        Box(modifier = Modifier.fillMaxSize().background(colorResource(id = R.color.purple_700))) {
                                        }
                                    }
                                    composable(MainBottomBarNavigation.Profile.root) {
                                        viewModel.onScreenChanged(MainScreenState.Profile)
                                        ProfileScreen(
                                            viewModel = hiltViewModel(),
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
                                        selected = currentDestination?.hierarchy?.any { it.route == screen.root } == true,
                                        onClick = {
                                            topBarTitle = getString(screen.resourceId)
                                            navController.navigate(screen.root) {
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

    private fun onSettingsClicked() {
        viewModel.logOut() // TODO will be changed
    }
}


sealed class MainBottomBarNavigation(val root: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    object Home : MainBottomBarNavigation("home", R.string.home, Icons.Filled.Home)
    object Body : MainBottomBarNavigation("body", R.string.body, Icons.Filled.AddCircle)
    object Exercises : MainBottomBarNavigation("exercises", R.string.exercises, Icons.Filled.Favorite)
    object Profile : MainBottomBarNavigation("profile", R.string.profile, Icons.Filled.Person)
}