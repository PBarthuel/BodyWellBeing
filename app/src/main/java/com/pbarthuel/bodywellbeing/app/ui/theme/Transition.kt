package com.pbarthuel.bodywellbeing.app.ui.theme

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween

@ExperimentalAnimationApi
fun AnimatedContentScope<*>.closeToRight(): ExitTransition {
    return slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(300))
}

@ExperimentalAnimationApi
fun AnimatedContentScope<*>.openFromRight(): EnterTransition {
    return slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(300))
}

@ExperimentalAnimationApi
fun AnimatedContentScope<*>.openFromLeft(): EnterTransition {
    return slideIntoContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(300))
}