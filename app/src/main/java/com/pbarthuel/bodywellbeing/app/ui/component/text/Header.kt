package com.pbarthuel.bodywellbeing.app.ui.component.text

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.pbarthuel.bodywellbeing.app.ui.theme.BodyWellBeingTheme
import com.pbarthuel.bodywellbeing.app.ui.theme.LocalPrimaryTextColor

enum class HeaderStyle {
    Header1,
    Header2,
    Header3,
    Header4,
    Eyebrow
}

@Composable
fun Header(
    modifier: Modifier = Modifier,
    text: String,
    style: HeaderStyle,
    textAlign: TextAlign? = null,
    color: Color = LocalPrimaryTextColor.current
) = Text(modifier = modifier,
        overflow = TextOverflow.Ellipsis,
        text = text,
        color = color,
        textAlign = textAlign,
        style = when (style) {
            HeaderStyle.Header1 -> BodyWellBeingTheme.types.header1
            HeaderStyle.Header2 -> BodyWellBeingTheme.types.header2
            HeaderStyle.Header3 -> BodyWellBeingTheme.types.header3
            HeaderStyle.Header4 -> BodyWellBeingTheme.types.header4
            HeaderStyle.Eyebrow -> BodyWellBeingTheme.types.header_eyebrow
        })

@Composable
fun Header(
    modifier: Modifier = Modifier,
    text: AnnotatedString,
    style: HeaderStyle,
    textAlign: TextAlign? = null,
    color: Color = LocalPrimaryTextColor.current
) = Text(modifier = modifier,
        overflow = TextOverflow.Ellipsis,
        text = text,
        color = color,
        textAlign = textAlign,
        style = when (style) {
            HeaderStyle.Header1 -> BodyWellBeingTheme.types.header1
            HeaderStyle.Header2 -> BodyWellBeingTheme.types.header2
            HeaderStyle.Header3 -> BodyWellBeingTheme.types.header3
            HeaderStyle.Header4 -> BodyWellBeingTheme.types.header4
            HeaderStyle.Eyebrow -> BodyWellBeingTheme.types.header_eyebrow
        })

@Composable
fun Header1(modifier: Modifier = Modifier, text: String) = Header(modifier, text, HeaderStyle.Header1)

@Composable
fun Header2(modifier: Modifier = Modifier, text: String, textAlign: TextAlign? = null) = Header(modifier, text, HeaderStyle.Header2, textAlign)

@Composable
fun Header3(modifier: Modifier = Modifier, text: String) = Header(modifier, text, HeaderStyle.Header3)

@Composable
fun Header4(modifier: Modifier = Modifier, text: String) = Header(modifier, text, HeaderStyle.Header4)

@Composable
fun Eyebrow(modifier: Modifier = Modifier, text: String) = Header(modifier, text, HeaderStyle.Eyebrow)

@Composable
fun Eyebrow(modifier: Modifier = Modifier, text: AnnotatedString) = Header(modifier, text, HeaderStyle.Eyebrow)
