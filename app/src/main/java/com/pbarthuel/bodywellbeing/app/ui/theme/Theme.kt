package com.pbarthuel.bodywellbeing.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp

private val LightColorPalette = BodyWellBeingColorPalette(
    textPrimary = AliasToken.textPrimary.light,
    textSecondary = AliasToken.textSecondary.light,
    textPlaceHolder = AliasToken.textPlaceholder.light,
    actionPrimaryBad = AliasToken.actionPrimaryBad.light,
    actionPrimaryDisabled = AliasToken.actionPrimaryDisabled.light,
    actionPrimaryUnchecked = AliasToken.actionPrimaryUnchecked.light,
    actionSecondary = AliasToken.actionSecondary.light,
    actionSecondaryDisabled = AliasToken.actionSecondaryDisabled.light,
    statusGood = AliasToken.statusGood.light,
    statusModerate = AliasToken.statusModerate.light,
    statusBad = AliasToken.statusBad.light,
    statusUndefined = AliasToken.statusUndefined.light,
    divider = AliasToken.dividerPrimary.light
)

private val DarkColorPalette = BodyWellBeingColorPalette(
    textPrimary = AliasToken.textPrimary.dark,
    textSecondary = AliasToken.textSecondary.dark,
    textPlaceHolder = AliasToken.textPlaceholder.dark,
    actionPrimaryBad = AliasToken.actionPrimaryBad.dark,
    actionPrimaryDisabled = AliasToken.actionPrimaryDisabled.dark,
    actionPrimaryUnchecked = AliasToken.actionPrimaryUnchecked.dark,
    actionSecondary = AliasToken.actionSecondary.dark,
    actionSecondaryDisabled = AliasToken.actionSecondaryDisabled.dark,
    statusGood = AliasToken.statusGood.dark,
    statusModerate = AliasToken.statusModerate.dark,
    statusBad = AliasToken.statusBad.dark,
    statusUndefined = AliasToken.statusUndefined.dark,
    divider = AliasToken.dividerPrimary.dark
)

private val MaterialLightPalette = lightColors(
    primary = AliasToken.actionPrimary.light,
    onPrimary = AliasToken.onActionPrimary.light,
    surface = AliasToken.backgroundSecondary.light,
    onSurface = AliasToken.textPrimary.light,
    error = AliasToken.actionPrimaryBad.light,
    onError = AliasToken.onActionPrimary.light,
    secondary = AliasToken.actionSecondary.light,
    onSecondary = AliasToken.onActionSecondary.light,
    background = AliasToken.backgroundPrimary.light
)

private val MaterialDarkPalette = darkColors(
    primary = AliasToken.actionPrimary.dark,
    onPrimary = AliasToken.onActionPrimary.dark,
    surface = AliasToken.backgroundSecondary.dark,
    onSurface = AliasToken.textPrimary.dark,
    error = AliasToken.actionPrimaryBad.dark,
    onError = AliasToken.onActionPrimary.dark,
    secondary = AliasToken.actionSecondary.dark,
    onSecondary = AliasToken.onActionSecondary.dark,
    background = AliasToken.backgroundPrimary.dark
)

private val BodyWellBeingShapes = Shapes(
    small = RoundedCornerShape(2.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(16.dp)
)

object BodyWellBeingTheme {

    val colors: BodyWellBeingColorPalette
        @Composable get() = LocalColorBodyWellBeing.current

    val types: BodyWellBeingTypeList
        @Composable get() = LocalTypeBodyWellBeing.current
}

@Composable
fun BodyWellBeingTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette
    val materialColors = if (darkTheme) MaterialDarkPalette else MaterialLightPalette
    val typeList = typeList()
    ProvideBodyWellBeing(colors, typeList) {
        MaterialTheme(
            colors = materialColors,
            shapes = BodyWellBeingShapes,
            content = content
        )
    }
}

@Composable
fun ProvideBodyWellBeing(
    colors: BodyWellBeingColorPalette,
    types: BodyWellBeingTypeList,
    content: @Composable () -> Unit
) {
    val colorPalette = remember { colors }
    colorPalette.update(colors)
    val typeList = remember { types }
    typeList.update(types)
    CompositionLocalProvider(
        LocalColorBodyWellBeing provides colorPalette,
        LocalTypeBodyWellBeing provides typeList,
        LocalPrimaryTextColor provides colorPalette.textPrimary,
        LocalSecondaryTextColor provides colorPalette.textSecondary,
        content = content
    )
}

private val LocalTypeBodyWellBeing = staticCompositionLocalOf<BodyWellBeingTypeList> {
    error("No BodyWellBeingTypeList provided")
}
val LocalColorBodyWellBeing = staticCompositionLocalOf<BodyWellBeingColorPalette> {
    error("No BodyWellBeingColorPalette provided")
}

class DarkThemePreviewParamProvider : CollectionPreviewParameterProvider<Boolean>(
    listOf(false, true)
)

@Stable
class BodyWellBeingColorPalette(
    textPrimary: Color,
    textSecondary: Color,
    textPlaceHolder: Color,
    actionPrimaryBad: Color,
    actionPrimaryDisabled: Color,
    actionPrimaryUnchecked: Color,
    actionSecondary: Color,
    actionSecondaryDisabled: Color,
    statusGood: Color,
    statusModerate: Color,
    statusBad: Color,
    statusUndefined: Color,
    divider: Color
) {
    var textPrimary by mutableStateOf(textPrimary)
        private set
    var textSecondary by mutableStateOf(textSecondary)
        private set
    var textPlaceHolder by mutableStateOf(textPlaceHolder)
        private set
    var actionPrimaryBad by mutableStateOf(actionPrimaryBad)
        private set
    var actionPrimaryDisabled by mutableStateOf(actionPrimaryDisabled)
        private set
    var actionPrimaryUnchecked by mutableStateOf(actionPrimaryUnchecked)
        private set
    var actionSecondary by mutableStateOf(actionSecondary)
        private set
    var actionSecondaryDisabled by mutableStateOf(actionSecondaryDisabled)
        private set
    var statusGood by mutableStateOf(statusGood)
        private set
    var statusModerate by mutableStateOf(statusModerate)
        private set
    var statusBad by mutableStateOf(statusBad)
        private set
    var statusUndefined by mutableStateOf(statusUndefined)
        private set
    var divider by mutableStateOf(divider)
        private set

    fun update(other: BodyWellBeingColorPalette) {
        textPrimary = other.textPrimary
        textSecondary = other.textSecondary
        textPlaceHolder = other.textPlaceHolder
        actionPrimaryBad = other.actionPrimaryBad
        actionPrimaryDisabled = other.actionPrimaryDisabled
        actionPrimaryUnchecked = other.actionPrimaryUnchecked
        actionSecondary = other.actionSecondary
        actionSecondaryDisabled = other.actionSecondaryDisabled
        statusGood = other.statusGood
        statusModerate = other.statusModerate
        statusBad = other.statusBad
        statusUndefined = other.statusUndefined
        divider = other.divider
    }
}

@Stable
class BodyWellBeingTypeList(
    glyph: TextStyle,
    body1: TextStyle,
    body2: TextStyle,
    data2: TextStyle,
    data3: TextStyle,
    data4: TextStyle,
    data5: TextStyle,
    data6: TextStyle,
    data7: TextStyle,
    detail1: TextStyle,
    header1: TextStyle,
    header2: TextStyle,
    header3: TextStyle,
    header4: TextStyle,
    button1: TextStyle,
    button2: TextStyle,
    detail2: TextStyle,
    header_eyebrow: TextStyle,
) {
    var glyph by mutableStateOf(glyph)
        private set
    var body1 by mutableStateOf(body1)
        private set
    var body2 by mutableStateOf(body2)
        private set
    var data2 by mutableStateOf(data2)
        private set
    var data3 by mutableStateOf(data3)
        private set
    var data4 by mutableStateOf(data4)
        private set
    var data5 by mutableStateOf(data5)
        private set
    var data6 by mutableStateOf(data6)
        private set
    var data7 by mutableStateOf(data7)
        private set
    var detail1 by mutableStateOf(detail1)
        private set
    var detail2 by mutableStateOf(detail2)
        private set
    var header1 by mutableStateOf(header1)
        private set
    var header2 by mutableStateOf(header2)
        private set
    var header3 by mutableStateOf(header3)
        private set
    var header4 by mutableStateOf(header4)
        private set
    var header_eyebrow by mutableStateOf(header_eyebrow)
        private set
    var button1 by mutableStateOf(button1)
        private set
    var button2 by mutableStateOf(button2)
        private set

    fun update(other: BodyWellBeingTypeList) {
        glyph = other.glyph
        body1 = other.body1
        body2 = other.body2
        data2 = other.data2
        data3 = other.data3
        data4 = other.data4
        data5 = other.data5
        data6 = other.data6
        data7 = other.data7
        detail1 = other.detail1
        detail2 = other.detail2
        header1 = other.header1
        header2 = other.header2
        header3 = other.header3
        header4 = other.header4
        header_eyebrow = other.header_eyebrow
        button1 = other.button1
        button2 = other.button2
    }
}