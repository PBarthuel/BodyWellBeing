package com.pbarthuel.bodywellbeing.app.ui.component.text

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import com.pbarthuel.bodywellbeing.app.ui.theme.BodyWellBeingTheme
import com.pbarthuel.bodywellbeing.app.ui.theme.LocalSecondaryTextColor

@Composable
fun Data(
    modifier: Modifier = Modifier,
    text: AnnotatedString,
    level: Int,
    color: Color = LocalSecondaryTextColor.current,
    textAlign: TextAlign? = null,
) = Text(modifier = modifier, text = text, color = color, textAlign = textAlign, style = when (level) {
    2 -> BodyWellBeingTheme.types.data2
    3 -> BodyWellBeingTheme.types.data3
    4 -> BodyWellBeingTheme.types.data4
    5 -> BodyWellBeingTheme.types.data5
    6 -> BodyWellBeingTheme.types.data6
    7 -> BodyWellBeingTheme.types.data7
    else -> throw IllegalStateException("No style for level $level")
})

@Composable
fun Data2(modifier: Modifier = Modifier, text: String, color: Color = LocalSecondaryTextColor.current) = Data(modifier, AnnotatedString(text), 2, color = color)

@Composable
fun Data3(modifier: Modifier = Modifier, text: String, color: Color = LocalSecondaryTextColor.current) = Data(modifier, AnnotatedString(text), 3, color = color)

@Composable
fun Data4(modifier: Modifier = Modifier, text: String, color: Color = LocalSecondaryTextColor.current) = Data(modifier, AnnotatedString(text), 4, color = color)

@Composable
fun Data5(modifier: Modifier = Modifier, text: String) = Data(modifier, AnnotatedString(text), 5)

@Composable
fun Data6(modifier: Modifier = Modifier, text: String, color: Color = LocalSecondaryTextColor.current) = Data(modifier, AnnotatedString(text), 6, color = color)

@Composable
fun Data7(modifier: Modifier = Modifier, text: String, color: Color = LocalSecondaryTextColor.current) = Data(modifier, AnnotatedString(text), 7, color)
