package com.pbarthuel.bodywellbeing.app.ui.component.text

import androidx.annotation.IntRange
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import com.pbarthuel.bodywellbeing.app.ui.theme.BodyWellBeingTheme
import com.pbarthuel.bodywellbeing.app.ui.theme.LocalSecondaryTextColor

@Composable
fun Detail(
    modifier: Modifier = Modifier,
    text: String,
    @IntRange(from = 1, to = 2) level: Int = 1,
    color: Color = LocalSecondaryTextColor.current,
    maxLines: Int = Int.MAX_VALUE
) {
    Detail(
        modifier = modifier,
        text = AnnotatedString(text),
        level = level,
        color = color,
        maxLines = maxLines
    )
}

@Composable
fun Detail(
    modifier: Modifier = Modifier,
    text: AnnotatedString,
    @IntRange(from = 1, to = 2) level: Int = 1,
    color: Color = LocalSecondaryTextColor.current,
    maxLines: Int = Int.MAX_VALUE
) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        maxLines = maxLines,
        style = when (level) {
            2 -> BodyWellBeingTheme.types.detail2
            else -> BodyWellBeingTheme.types.detail1
        }
    )
}

@Composable
fun Detail1(modifier: Modifier = Modifier, text: String, color: Color = LocalSecondaryTextColor.current) = Detail1(modifier, AnnotatedString(text), color = color)

@Composable
fun Detail1(modifier: Modifier = Modifier, text: AnnotatedString, color: Color = LocalSecondaryTextColor.current) = Detail(modifier, text, 1, color = color)

@Composable
fun Detail2(modifier: Modifier = Modifier, text: String, color: Color = LocalSecondaryTextColor.current) = Detail2(modifier, AnnotatedString(text), color = color)

@Composable
fun Detail2(modifier: Modifier = Modifier, text: AnnotatedString, color: Color = LocalSecondaryTextColor.current) = Detail(modifier, text, 2, color = color)
