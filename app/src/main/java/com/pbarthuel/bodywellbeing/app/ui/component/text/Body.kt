package com.pbarthuel.bodywellbeing.app.ui.component.text

import androidx.annotation.IntRange
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.pbarthuel.bodywellbeing.app.ui.theme.BodyWellBeingTheme
import com.pbarthuel.bodywellbeing.app.ui.theme.LocalSecondaryTextColor

@Composable
fun Body(
    modifier: Modifier = Modifier,
    text: String,
    @IntRange(from = 1, to = 2) level: Int = 1,
    color: Color = LocalSecondaryTextColor.current,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = null,
) = Text(
    modifier = modifier,
    text = text,
    color = color,
    style = when (level) {
        2 -> BodyWellBeingTheme.types.body2
        else -> BodyWellBeingTheme.types.body1
    },
    maxLines = maxLines,
    overflow = TextOverflow.Ellipsis,
    textAlign = textAlign
)

@Composable
fun Body(
    modifier: Modifier = Modifier,
    text: AnnotatedString,
    @IntRange(from = 1, to = 2) level: Int = 1,
    color: Color = LocalSecondaryTextColor.current,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = null,
) = Text(
    modifier = modifier,
    text = text,
    color = color,
    style = when (level) {
        2 -> BodyWellBeingTheme.types.body2
        else -> BodyWellBeingTheme.types.body1
    },
    maxLines = maxLines,
    overflow = TextOverflow.Ellipsis,
    textAlign = textAlign,
)

@Composable
fun Body1(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = LocalSecondaryTextColor.current,
    textAlign: TextAlign? = null,
) = Body(modifier, text, 1, color, textAlign = textAlign)

@Composable
fun Body2(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = LocalSecondaryTextColor.current,
    textAlign: TextAlign? = null,
) = Body(modifier, text, 2, color, textAlign = textAlign)

@Composable
fun Body1(
    modifier: Modifier = Modifier,
    text: AnnotatedString,
    color: Color = LocalSecondaryTextColor.current,
    textAlign: TextAlign? = null,
) = Body(modifier, text, 1, color, textAlign = textAlign)

@Composable
fun Body2(
    modifier: Modifier = Modifier,
    text: AnnotatedString,
    color: Color = LocalSecondaryTextColor.current,
    textAlign: TextAlign? = null,
) = Body(modifier, text, 2, color, textAlign = textAlign)
