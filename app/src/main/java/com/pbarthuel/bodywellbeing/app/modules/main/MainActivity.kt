package com.pbarthuel.bodywellbeing.app.modules.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.pbarthuel.bodywellbeing.R
import com.pbarthuel.bodywellbeing.app.model.CondenseExercise
import com.pbarthuel.bodywellbeing.app.modules.articleDetail.ArticleDetailActivity
import com.pbarthuel.bodywellbeing.app.modules.createExercise.CreateExerciseActivity
import com.pbarthuel.bodywellbeing.app.modules.exerciseDetail.ClassicExerciseDetailActivity
import com.pbarthuel.bodywellbeing.app.modules.exerciseDetail.CustomExerciseDetailActivity
import com.pbarthuel.bodywellbeing.app.modules.home.HomeScreenWithoutEnrolledProgram
import com.pbarthuel.bodywellbeing.app.modules.infos.InfosScreen
import com.pbarthuel.bodywellbeing.app.modules.profile.ProfileScreen
import com.pbarthuel.bodywellbeing.app.modules.programOverview.ProgramOverviewActivity
import com.pbarthuel.bodywellbeing.app.modules.settings.SettingsActivity
import com.pbarthuel.bodywellbeing.app.ui.component.text.Header2
import com.pbarthuel.bodywellbeing.app.ui.theme.Basic1
import com.pbarthuel.bodywellbeing.app.ui.theme.BodyWellBeingTheme
import com.pbarthuel.bodywellbeing.app.ui.theme.openFromRight
import com.pbarthuel.bodywellbeing.viewModel.modules.main.MainScreenState
import com.pbarthuel.bodywellbeing.viewModel.modules.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    companion object {
        const val EXTRA_EXERCISE_ID = "exerciseId"
        const val EXTRA_PROGRAM_ID = "programId"
        const val EXTRA_ARTICLE_ID = "articleId"
    }

    private val viewModel by viewModels<MainViewModel>()

    private val activityTrackPermissionState: MutableState<Boolean> = mutableStateOf(false)
    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean -> activityTrackPermissionState.value = isGranted }

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.syncExercise()
        viewModel.syncProgram()
        viewModel.syncArticle()
        checkActivityTackPermissionStatus()

        setContent {
            BodyWellBeingTheme {
                ProvideWindowInsets {
                    BackHandler { }
                    val navController = rememberAnimatedNavController()
                    val bottomNavigationItems = listOf(
                        Destinations.MainBottomBarNavigation.Home,
                        Destinations.MainBottomBarNavigation.Infos,
                        Destinations.MainBottomBarNavigation.Profile
                    )
                    val screenState = viewModel.screenState.collectAsState()
                    val isUserAdmin = viewModel.isUserAdmin().collectAsState(initial = false)
                    var topBarTitle by remember { mutableStateOf(getString(R.string.home)) }
                    var shouldShowBars by remember { mutableStateOf(true) }
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        topBar = {
                            AnimatedVisibility(
                                visible = shouldShowBars,
                                enter = fadeIn(animationSpec = tween(200)),
                                exit = fadeOut(animationSpec = tween(200))
                            ) {
                                TopAppBar(
                                    modifier = Modifier.padding(Basic1),
                                    backgroundColor = Color.Transparent.copy(alpha = 0f),
                                    elevation = 2.dp,
                                    title = { Header2(text = topBarTitle) },
                                    actions = {
                                        when (screenState.value) {
                                            MainScreenState.Home -> {
                                                if (isUserAdmin.value == true) {
                                                    IconButton(onClick = { }) {
                                                        Icon(
                                                            Icons.Filled.AddCircle,
                                                            contentDescription = "Create Program"
                                                        )
                                                    }
                                                }
                                            }
                                            MainScreenState.Profile -> {
                                                IconButton(onClick = { onSettingsClicked() }) {
                                                    Icon(
                                                        Icons.Filled.Settings,
                                                        contentDescription = "Settings"
                                                    )
                                                }
                                            }
                                            MainScreenState.Exercises -> {
                                                IconButton(onClick = { onCreateExerciseClicked() }) {
                                                    Icon(
                                                        Icons.Filled.AddCircle,
                                                        contentDescription = "Create Exercise"
                                                    )
                                                }
                                            }
                                            else -> {}
                                        }
                                    }
                                )
                            }
                        }
                    ) {
                        Column(modifier = Modifier.fillMaxSize()) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .weight(1f)
                            ) {
                                AnimatedNavHost(
                                    navController,
                                    startDestination = Destinations.MainBottomBarNavigation.Home.root,
                                    enterTransition = { openFromRight() },
                                    exitTransition = { fadeOut(animationSpec = tween(300)) },
                                    popEnterTransition = { openFromRight() },
                                    popExitTransition = { fadeOut(animationSpec = tween(300)) }
                                ) {
                                    composable(Destinations.MainBottomBarNavigation.Home.root) {
                                        viewModel.onScreenChanged(MainScreenState.Home)
                                        shouldShowBars = true
                                        HomeScreenWithoutEnrolledProgram(
                                            activityTrackPermissionState = activityTrackPermissionState,
                                            onStepGaugeClick = { onStepGaugeClicked() },
                                            onProgramCardClicked = {
                                                startActivity(
                                                    Intent(this@MainActivity, ProgramOverviewActivity::class.java)
                                                        .putExtra(EXTRA_PROGRAM_ID, it.programId)
                                                )
                                            }
                                        )
                                    }
                                    composable(Destinations.MainBottomBarNavigation.Infos.root) {
                                        viewModel.onScreenChanged(MainScreenState.Exercises)
                                        shouldShowBars = true
                                        InfosScreen(
                                            onExerciseCardClicked = { exercise ->
                                                onExerciseCardClicked(condenseExercise = exercise)
                                            },
                                            onArticleCardClicked = { article ->
                                                startActivity(
                                                    Intent(this@MainActivity, ArticleDetailActivity::class.java)
                                                        .putExtra(EXTRA_ARTICLE_ID, article.id)
                                                )
                                            }
                                        )
                                    }
                                    composable(Destinations.MainBottomBarNavigation.Profile.root) {
                                        viewModel.onScreenChanged(MainScreenState.Profile)
                                        shouldShowBars = true
                                        ProfileScreen(
                                            viewModel = hiltViewModel(),
                                            onExerciseCardClicked = { exercise ->
                                                onExerciseCardClicked(condenseExercise = exercise)
                                            },
                                            onArticleCardClicked = { article ->
                                                startActivity(
                                                    Intent(this@MainActivity, ArticleDetailActivity::class.java)
                                                        .putExtra(EXTRA_ARTICLE_ID, article.id)
                                                )
                                            }
                                        )
                                    }
                                }
                            }
                            AnimatedVisibility(
                                visible = shouldShowBars,
                                enter = fadeIn(animationSpec = tween(200)),
                                exit = fadeOut(animationSpec = tween(200))
                            ) {
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
    }

    override fun onRestart() {
        super.onRestart()
        checkActivityTackPermissionStatus()


    }

    override fun onResume() {
        super.onResume()
        checkActivityTackPermissionStatus()
    }

    private fun onExerciseCardClicked(condenseExercise: CondenseExercise) {
        when (condenseExercise) {
            is CondenseExercise.Classic -> startActivity(
                Intent(this@MainActivity, ClassicExerciseDetailActivity::class.java)
                    .putExtra(EXTRA_EXERCISE_ID, condenseExercise.id)
            )
            is CondenseExercise.Custom -> startActivity(
                Intent(this@MainActivity, CustomExerciseDetailActivity::class.java)
                    .putExtra(EXTRA_EXERCISE_ID, condenseExercise.id)
            )
        }
    }

    private fun onSettingsClicked() {
        startActivity(Intent(this@MainActivity, SettingsActivity::class.java))
    }

    private fun onCreateExerciseClicked() {
        startActivity(Intent(this@MainActivity, CreateExerciseActivity::class.java))
    }

    private fun checkActivityTackPermissionStatus() {
        activityTrackPermissionState.value = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            when (PackageManager.PERMISSION_GRANTED) {
                ContextCompat.checkSelfPermission(
                    this@MainActivity,
                    Manifest.permission.ACTIVITY_RECOGNITION
                ) -> true
                else -> false
            }
        } else { true }
    }

    private fun onStepGaugeClicked() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            when {
                ContextCompat.checkSelfPermission(
                    this@MainActivity,
                    Manifest.permission.ACTIVITY_RECOGNITION
                ) == PackageManager.PERMISSION_GRANTED -> {
                    Toast.makeText(this@MainActivity, "Already granted", Toast.LENGTH_SHORT).show()
                }
                shouldShowRequestPermissionRationale(Manifest.permission.ACTIVITY_RECOGNITION) -> {
                    Toast.makeText(this@MainActivity, "show rational", Toast.LENGTH_SHORT).show()
                }
                else -> { requestPermissionLauncher.launch(Manifest.permission.ACTIVITY_RECOGNITION) }
            }
        }
    }
}

object Destinations {
    sealed class MainBottomBarNavigation(
        val root: String,
        @StringRes val resourceId: Int,
        val icon: ImageVector
    ) {
        object Home : MainBottomBarNavigation("home", R.string.home, Icons.Filled.Home)
        object Infos : MainBottomBarNavigation("infos", R.string.infos, Icons.Filled.Favorite)

        object Profile : MainBottomBarNavigation("profile", R.string.profile, Icons.Filled.Person)
    }
}