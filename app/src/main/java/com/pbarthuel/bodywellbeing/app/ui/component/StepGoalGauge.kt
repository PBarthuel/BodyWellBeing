package com.pbarthuel.bodywellbeing.app.ui.component

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.FloatRange
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.pbarthuel.bodywellbeing.app.ui.component.text.Data6
import com.pbarthuel.bodywellbeing.app.ui.component.text.Detail2
import com.pbarthuel.bodywellbeing.app.ui.theme.BodyWellBeingTheme

@Composable
fun StepGoalGauge(
    modifier: Modifier = Modifier,
    canvasModifier: Modifier = Modifier,
    @FloatRange(from = 0.0, to = 1.0) progress: Float,
    textInside: String,
    title: String,
    animate: Boolean = false
) {
    val backgroundColor = MaterialTheme.colors.background
    val dividerColor = BodyWellBeingTheme.colors.divider
    val gradient1 = BodyWellBeingTheme.colors.gradient1
    val gradient2 = BodyWellBeingTheme.colors.gradient2
    val circleLayoutSize = 140.dp
    val circleStrokeWidth = 12.dp
    val circleInsideStrokeWidth = circleStrokeWidth - 2.dp
    val startDegree = -210f
    val maxDegree = 240f
    var animationState by remember { mutableStateOf(false) }
    val animationProgress: Float by animateFloatAsState(
        targetValue = if (animationState) progress else 0f,
        animationSpec =
        when (animate) {
            true -> tween(durationMillis = 300, delayMillis = 50, easing = LinearOutSlowInEasing)
            else -> snap(delayMillis = 0)
        }
    )
    SideEffect {
        animationState = true
    }
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        ConstraintLayout {
            val (canvas, detail) = createRefs()
            Canvas(
                canvasModifier
                    .size(circleLayoutSize)
                    .padding(circleStrokeWidth / 2)
                    .constrainAs(canvas) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                    }
            ) {
                drawArc(
                    color = dividerColor,
                    startAngle = startDegree,
                    sweepAngle = maxDegree,
                    useCenter = false,
                    style = Stroke(width = circleStrokeWidth.toPx(), cap = StrokeCap.Round)
                )
                drawArc(
                    color = backgroundColor,
                    startAngle = startDegree,
                    sweepAngle = maxDegree,
                    useCenter = false,
                    style = Stroke(width = circleInsideStrokeWidth.toPx(), cap = StrokeCap.Round)
                )
                drawArc(
                    brush = Brush.horizontalGradient(listOf(gradient2, gradient1)),
                    startAngle = startDegree,
                    sweepAngle = maxDegree * animationProgress,
                    useCenter = false,
                    style = Stroke(width = circleStrokeWidth.toPx(), cap = StrokeCap.Round)
                )
            }
            Detail2(
                Modifier
                    .padding(bottom = circleStrokeWidth / 2)
                    .constrainAs(detail) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    },
                text = title
            )
        }
        Data6(text = textInside)
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Composable
fun Preview_30() {
    BodyWellBeingTheme {
        StepGoalGauge(progress = 0.3f, textInside = "24", title = "/80 min")
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Composable
fun Preview_70() {
    BodyWellBeingTheme {
        StepGoalGauge(progress = 0.7f, textInside = "56", title = "/80 min")
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Composable
fun Preview_80() {
    BodyWellBeingTheme {
        StepGoalGauge(progress = 1f, textInside = "80", title = "/80 min")
    }
}