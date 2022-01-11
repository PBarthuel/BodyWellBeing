package com.pbarthuel.bodywellbeing.app.ui.component.input

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.pbarthuel.bodywellbeing.app.ui.component.text.Body
import com.pbarthuel.bodywellbeing.app.ui.component.text.Detail
import com.pbarthuel.bodywellbeing.app.ui.component.text.Eyebrow
import com.pbarthuel.bodywellbeing.app.ui.theme.Basic1
import com.pbarthuel.bodywellbeing.app.ui.theme.Basic2
import com.pbarthuel.bodywellbeing.app.ui.theme.Basic4
import com.pbarthuel.bodywellbeing.app.ui.theme.BodyWellBeingTheme
import com.pbarthuel.bodywellbeing.app.ui.theme.DarkThemePreviewParamProvider
import com.pbarthuel.bodywellbeing.app.ui.theme.Layout3
import com.pbarthuel.bodywellbeing.app.ui.theme.Layout5
import com.pbarthuel.bodywellbeing.app.ui.theme.LocalPrimaryTextColor
import com.pbarthuel.bodywellbeing.app.ui.theme.LocalSecondaryTextColor
import java.util.*

enum class InputFieldType(
    val keyboardType: KeyboardType,
    val keyboardCapitalization: KeyboardCapitalization = KeyboardCapitalization.None
) {
    Text(KeyboardType.Text, KeyboardCapitalization.Sentences),
    Email(KeyboardType.Email),
    Number(KeyboardType.Number),
    Password(KeyboardType.Password),
    PasswordClear(KeyboardType.Text, KeyboardCapitalization.None),
    Phone(KeyboardType.Phone),
}

fun InputFieldType.getVisualTransformation(): VisualTransformation {
    return when (this) {
        InputFieldType.Password -> PasswordVisualTransformation()
        else -> VisualTransformation.None
    }
}

@Composable
fun InputField(
    modifier: Modifier = Modifier,
    inputType: InputFieldType,
    internalPadding: PaddingValues = PaddingValues(InternalFieldPadding),
    text: String = "",
    visualTransformation: VisualTransformation? = null,
    placeHolder: String? = null,
    leftAccessory: (@Composable () -> Unit)? = null,
    rightAccessory: (@Composable () -> Unit)? = null,
    isError: Boolean = false,
    imeAction: ImeAction = ImeAction.Next,
    onImeAction: () -> Unit = {},
    focusRequester: FocusRequester = FocusRequester(),
    enabled: Boolean = true,
    onValueChange: (String) -> Unit,
    onFocusChange: (Boolean) -> Unit
) {

    var isFocused by remember { mutableStateOf(false) }
    val borderColor = when {
        isFocused -> MaterialTheme.colors.primary
        isError -> MaterialTheme.colors.error
        text.isBlank() -> BodyWellBeingTheme.colors.textPlaceHolder
        else -> LocalPrimaryTextColor.current
    }
    val textStyle = BodyWellBeingTheme.types.body1.copy(
        color = when {
            isError && isFocused.not() -> MaterialTheme.colors.error
            text.isBlank() -> BodyWellBeingTheme.colors.textPlaceHolder
            else -> LocalPrimaryTextColor.current
        }
    )

    BasicTextField(
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fieldBorder(borderColor = borderColor, internalPadding),
                verticalAlignment = Alignment.CenterVertically
            ) {
                leftAccessory?.let {
                    it()
                    Spacer(Modifier.width(Basic4))
                }
                Box(modifier = Modifier.weight(1f)) {
                    innerTextField()
                    placeHolder?.let {
                        Placeholder(
                            value = if (text.isEmpty()) it else "",
                            isError = isError,
                            isFocused = isFocused
                        )
                    }
                }
                rightAccessory?.let {
                    Spacer(Modifier.width(Basic4))
                    it()
                }
            }
        },
        value = text,
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester)
            .onFocusChanged {
                isFocused = it.isFocused
                onFocusChange.invoke(isFocused)
            }
            .then(modifier),
        textStyle = textStyle,
        cursorBrush = SolidColor(LocalPrimaryTextColor.current),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = inputType.keyboardType,
            capitalization = inputType.keyboardCapitalization,
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(onAny = { onImeAction() }),
        visualTransformation = visualTransformation ?: inputType.getVisualTransformation(),
        onValueChange = { onValueChange(it) },
        enabled = enabled
    )
}

@Composable
fun FormInputField(
    modifier: Modifier = Modifier,
    type: InputFieldType,
    internalPadding: PaddingValues = PaddingValues(InternalFieldPadding),
    label: String? = null,
    text: String = "",
    helperText: AnnotatedString? = null,
    placeHolder: String = "",
    leftAccessory: (@Composable () -> Unit)? = null,
    rightAccessory: (@Composable () -> Unit)? = null,
    gaugeLevel: Int? = null,
    isError: Boolean = false,
    focusRequester: FocusRequester = FocusRequester(),
    onFocusChange: (Boolean) -> Unit = {},
    imeAction: ImeAction = ImeAction.Next,
    visualTransformation: VisualTransformation? = null,
    enabled: Boolean = true,
    onImeAction: () -> Unit = {},
    onValueChange: (String) -> Unit = {}
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(Basic2)) {
        label?.takeIf { it.trim().isNotEmpty() }?.let {
            Eyebrow(text = it.uppercase(Locale.getDefault()))
        }
        InputField(
            inputType = type, internalPadding = internalPadding, text = text, placeHolder = placeHolder, leftAccessory = leftAccessory, rightAccessory = rightAccessory,
            isError = isError, imeAction = imeAction, onImeAction = onImeAction, focusRequester = focusRequester, onValueChange = onValueChange,
            onFocusChange = onFocusChange, enabled = enabled, visualTransformation = visualTransformation
        )
        gaugeLevel?.let {
            Gauge(level = it)
        }
        helperText?.takeIf { it.text.trim().isNotEmpty() }?.let {
            FormFieldHelperText(text = it, isError = isError)
        }
    }
}

@Composable
fun FormFieldHelperText(text: AnnotatedString, isError: Boolean) {
    Detail(
        text = text,
        level = 2,
        color = if (isError) MaterialTheme.colors.error else LocalSecondaryTextColor.current,
    )
}

@Composable
private fun Placeholder(value: String, isError: Boolean, isFocused: Boolean) {
    Body(
        text = value,
        level = 1,
        color = if (isError && isFocused.not()) MaterialTheme.colors.error else BodyWellBeingTheme.colors.textPlaceHolder,
        maxLines = 1,
    )
}

private const val GAUGE_NB_SEGMENTS = 3

@Composable
private fun Gauge(level: Int) {
    val defaultColor = BodyWellBeingTheme.colors.statusUndefined
    val levelColor = when {
        level > 1 -> BodyWellBeingTheme.colors.statusGood
        level > 0 -> BodyWellBeingTheme.colors.statusModerate
        else -> BodyWellBeingTheme.colors.statusBad
    }
    Row(modifier = Modifier.fillMaxWidth()) {
        for (i in 0 until GAUGE_NB_SEGMENTS) {
            Box(
                modifier = Modifier
                    .height(Basic1)
                    .weight(1f)
                    .padding(end = if (i != GAUGE_NB_SEGMENTS - 1) Basic1 else 0.dp)
                    .background(color = if (i > level) defaultColor else levelColor, shape = MaterialTheme.shapes.small)
            )
        }
    }
}

@SuppressLint("UnnecessaryComposedModifier")
fun Modifier.fieldBorder(borderColor: Color? = null, internalPadding: PaddingValues = PaddingValues(
    InternalFieldPadding
)) = composed {
    heightIn(Layout5)
        .background(color = MaterialTheme.colors.surface, shape = MaterialTheme.shapes.medium)
        .border(BorderStroke(1.dp, borderColor ?: LocalSecondaryTextColor.current), shape = MaterialTheme.shapes.medium)
        .padding(internalPadding)
}

@Preview(name = "FormInputVariants", showBackground = true)
@Composable
fun PreviewFormInputVariants(@PreviewParameter(DarkThemePreviewParamProvider::class) isDarkTheme: Boolean) {
    BodyWellBeingTheme(darkTheme = isDarkTheme) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(Basic4)
                .background(color = MaterialTheme.colors.background),
            verticalArrangement = Arrangement.spacedBy(Layout3)
        ) {
            FormInputField(
                type = InputFieldType.Text,
                label = "",
                placeHolder = "Placeholder"
            ) {}
            FormInputField(
                type = InputFieldType.Email,
                label = "Label",
                text = "jane.dow@withings.com",
                placeHolder = "Placeholder"
            ) {}
            FormInputField(
                type = InputFieldType.Text,
                label = "",
                placeHolder = "Placeholder Placeholder Placeholder Placeholder Placeholder Placeholder Placeholder ",
                helperText = AnnotatedString("This is a help text, used to clarify the intended use of the cell above. It should not be more than 2 lines.")
            ) {}
            FormInputField(
                type = InputFieldType.Text,
                label = "Label",
                placeHolder = "Placeholder",
                helperText = AnnotatedString("This is a help text, used to clarify the intended use of the cell above. It should not be more than 2 lines.")
            ) {}
            FormInputField(
                type = InputFieldType.Text,
                label = "Label",
                placeHolder = "Placeholder",
                helperText = AnnotatedString("This is a help text, used to clarify the intended use of the cell above. It should not be more than 2 lines."),
                isError = true
            ) {}
            FormInputField(
                type = InputFieldType.Password,
                label = "Password",
                text = "myTopSecretPassword",
                placeHolder = "Placeholder",
                helperText = AnnotatedString("Your password must have at least 8 characters, a mixture of numbers and letters and at least one special character."),
                rightAccessory = {
                    Icon(
                        imageVector = Icons.Rounded.Lock,
                        contentDescription = "Icon",
                        tint = MaterialTheme.colors.onSurface
                    )
                },
                gaugeLevel = 2
            ) {}
            FormInputField(
                type = InputFieldType.Phone,
                label = "Phone number",
                placeHolder = "+33",
                text = "+33761139943",
                leftAccessory = {
                    Icon(
                        imageVector = Icons.Rounded.Call,
                        contentDescription = "Icon",
                        tint = MaterialTheme.colors.onSurface
                    )
                }
            ) {}
        }
    }
}

private val InternalFieldPadding = Basic4
