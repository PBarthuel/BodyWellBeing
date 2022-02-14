package com.pbarthuel.bodywellbeing.app.ui.component.button

import androidx.annotation.IntRange
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.pbarthuel.bodywellbeing.app.ui.theme.Basic4
import com.pbarthuel.bodywellbeing.app.ui.theme.BodyWellBeingTheme
import com.pbarthuel.bodywellbeing.app.ui.theme.DarkThemePreviewParamProvider
import com.pbarthuel.bodywellbeing.app.ui.theme.Layout1
import com.pbarthuel.bodywellbeing.app.ui.theme.Layout4

enum class ColorStyle {
    Default,
    Bad,
    Quiet
}

enum class ButtonSize(
    val contentPadding: PaddingValues,
    val sizeModifier: Modifier,
    @IntRange(from = 1, to = 2) val buttonTextLevel: Int
) {
    Default(DefaultButtonContentPadding, Modifier.fillMaxWidth(), buttonTextLevel = 1),
    Mini(
        MiniButtonContentPadding, Modifier
            .wrapContentWidth()
            .heightIn(min = 24.dp), buttonTextLevel = 2
    )
}

@Composable
fun ButtonFill(
    modifier: Modifier = Modifier,
    text: String,
    startIconSpec: IconSpec? = null,
    enabled: Boolean = true,
    colorStyle: ColorStyle = ColorStyle.Default,
    buttonSize: ButtonSize = ButtonSize.Default,
    isLoading: Boolean = false,
    onClick: () -> Unit
) {
    val colors = buttonColors(fill = true, colorStyle = colorStyle)
    val contentColor: Color by colors.contentColor(enabled = enabled)
    Button(
        modifier = buttonSize.sizeModifier.then(modifier),
        onClick = { if (!isLoading) onClick.invoke() },
        enabled = enabled,
        shape = if (buttonSize == ButtonSize.Mini) MaterialTheme.shapes.medium else MaterialTheme.shapes.large,
        elevation = null,
        colors = colors,
        contentPadding = buttonSize.contentPadding,
        content = { ButtonContent(isLoading, contentColor, startIconSpec, text, buttonSize) }
    )
}

@Composable
fun ButtonOutlined(
    modifier: Modifier = Modifier,
    text: String,
    startIconSpec: IconSpec? = null,
    enabled: Boolean = true,
    colorStyle: ColorStyle = ColorStyle.Default,
    buttonSize: ButtonSize = ButtonSize.Default,
    isLoading: Boolean = false,
    onClick: () -> Unit
) {
    val colors = buttonColors(fill = false, colorStyle = colorStyle)
    val contentAndBorderColor: Color by colors.contentColor(enabled = enabled)
    OutlinedButton(
        modifier = buttonSize.sizeModifier.then(modifier),
        onClick = { if (!isLoading) onClick.invoke() },
        enabled = enabled,
        shape = if (buttonSize == ButtonSize.Mini) MaterialTheme.shapes.medium else MaterialTheme.shapes.large,
        elevation = null,
        colors = buttonColors(fill = false, colorStyle = colorStyle),
        border = BorderStroke(1.dp, contentAndBorderColor),
        contentPadding = buttonSize.contentPadding,
        content = { ButtonContent(isLoading, contentAndBorderColor, startIconSpec, text, buttonSize) }
    )
}

@Composable
private fun RowScope.ButtonContent(
    isLoading: Boolean,
    contentColor: Color,
    startIconSpec: IconSpec?,
    text: String,
    buttonSize: ButtonSize
) {
    Crossfade(targetState = isLoading) { loadingState ->
        when {
            loadingState -> ButtonLoader(contentColor)
            else -> Row(
                Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(Basic4, Alignment.CenterHorizontally)
            ) {
                startIconSpec?.ToComposable(contentColor)
                TextContent(text, contentColor, buttonSize.buttonTextLevel)
            }
        }
    }
}

@Composable
private fun TextContent(text: String, color: Color, @IntRange(from = 1, to = 2) level: Int = 1) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        style = when (level) {
            2 -> BodyWellBeingTheme.types.button2
            else -> BodyWellBeingTheme.types.button1
        },
        color = color
    )
}

@Composable
private fun buttonColors(
    fill: Boolean,
    colorStyle: ColorStyle,
): ButtonColors {
    val backgroundColor: Color
    val contentColor: Color
    val disabledContentColor: Color
    val disabledBackgroundColor: Color
    if (fill) {
        backgroundColor = when (colorStyle) {
            ColorStyle.Default -> MaterialTheme.colors.primary
            ColorStyle.Bad -> BodyWellBeingTheme.colors.actionPrimaryBad
            ColorStyle.Quiet -> MaterialTheme.colors.secondary
        }
        disabledBackgroundColor = when (colorStyle) {
            ColorStyle.Default -> BodyWellBeingTheme.colors.actionPrimaryDisabled
            ColorStyle.Bad -> MaterialTheme.colors.error.copy(alpha = 0.6f)
            ColorStyle.Quiet -> BodyWellBeingTheme.colors.actionSecondaryDisabled
        }
        contentColor = when (colorStyle) {
            ColorStyle.Default,
            ColorStyle.Bad -> MaterialTheme.colors.onPrimary
            ColorStyle.Quiet -> MaterialTheme.colors.onSecondary
        }
        disabledContentColor = when (colorStyle) {
            ColorStyle.Default -> Color.DarkGray
            else -> contentColor
        }
    } else {
        backgroundColor = Color.Transparent
        disabledBackgroundColor = Color.Transparent
        contentColor = when (colorStyle) {
            ColorStyle.Bad -> BodyWellBeingTheme.colors.actionPrimaryBad
            else -> MaterialTheme.colors.primary
        }
        disabledContentColor = contentColor.copy(alpha = 0.6f)
    }
    return buttonColors(
        backgroundColor = backgroundColor,
        disabledBackgroundColor = disabledBackgroundColor,
        contentColor = contentColor,
        disabledContentColor = disabledContentColor
    )
}

@Preview(name = "Primary Buttons", showBackground = true)
@Composable
fun PreviewPrimaryButtonFillDefault(@PreviewParameter(DarkThemePreviewParamProvider::class) isDarkTheme: Boolean) {
    BodyWellBeingTheme(darkTheme = isDarkTheme) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background)
        ) {
            ButtonFill(text = "Button Fill Default", onClick = {})
            Spacer(Modifier.height(Layout1))
            ButtonFill(text = "Button With Icon", onClick = {}, startIconSpec = IconSpec.IconImageVector(Icons.Rounded.CheckCircle))
            Spacer(Modifier.height(Layout1))
            ButtonFill(text = "Button Fill Bad", colorStyle = ColorStyle.Bad, onClick = {})
            Spacer(Modifier.height(Layout1))
            ButtonFill(text = "Button Fill Quiet", colorStyle = ColorStyle.Quiet, onClick = {})
            Spacer(Modifier.height(Layout1))
            ButtonFill(
                text = "Button With Icon",
                onClick = {},
                colorStyle = ColorStyle.Quiet,
                startIconSpec = IconSpec.IconImageVector(Icons.Rounded.CheckCircle)
            )
            Spacer(Modifier.height(Layout1))
            ButtonOutlined(text = "Button Outlined Default", onClick = {})
            Spacer(Modifier.height(Layout1))
            ButtonOutlined(text = "Button Outlined Bad", colorStyle = ColorStyle.Bad, onClick = {})

            Spacer(Modifier.height(Layout4))
            ButtonFill(text = "Button Disabled", enabled = false, onClick = {})
            Spacer(Modifier.height(Layout1))
            ButtonFill(text = "Button Disabled Bad", enabled = false, colorStyle = ColorStyle.Bad, onClick = {})
            Spacer(Modifier.height(Layout1))
            ButtonOutlined(text = "Button Outlined Disabled", enabled = false, onClick = {})
            Spacer(Modifier.height(Layout1))
            ButtonOutlined(text = "Button Outlined Disabled Bad", enabled = false, colorStyle = ColorStyle.Bad, onClick = {})
        }
    }
}

sealed class IconSpec {
    data class IconPainter(val painter: Painter) : IconSpec()
    data class IconImageVector(val vector: ImageVector) : IconSpec()
}

@Composable
fun IconSpec.ToComposable(tint: Color) {
    when (this) {
        is IconSpec.IconPainter -> Image(
            painter = painter,
            colorFilter = ColorFilter.tint(tint),
            contentDescription = "",
            modifier = Modifier.size(24.dp)
        )
        is IconSpec.IconImageVector -> Icon(
            imageVector = vector,
            tint = tint,
            contentDescription = ""
        )
    }
}

private val DefaultContentPaddingHorizontal = 20.dp
private val MiniContentPaddingHorizontal = 16.dp
private val DefaultContentPaddingVertical = 16.dp
private val SecondaryContentPaddingVertical = 12.dp
private val MiniContentPaddingVertical = 4.dp

private val DefaultButtonContentPadding = PaddingValues(
    start = DefaultContentPaddingHorizontal,
    top = DefaultContentPaddingVertical,
    end = DefaultContentPaddingHorizontal,
    bottom = DefaultContentPaddingVertical
)

private val SecondaryButtonContentPadding = PaddingValues(
    start = DefaultContentPaddingHorizontal,
    top = SecondaryContentPaddingVertical,
    end = DefaultContentPaddingHorizontal,
    bottom = SecondaryContentPaddingVertical
)

private val MiniButtonContentPadding = PaddingValues(
    start = MiniContentPaddingHorizontal,
    top = MiniContentPaddingVertical,
    end = MiniContentPaddingHorizontal,
    bottom = MiniContentPaddingVertical
)
